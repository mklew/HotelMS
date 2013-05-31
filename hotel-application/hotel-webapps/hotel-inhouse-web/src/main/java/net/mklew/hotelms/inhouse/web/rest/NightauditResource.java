package net.mklew.hotelms.inhouse.web.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.NoShowService;
import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.domain.booking.reservation.ReservationRepository;
import net.mklew.hotelms.inhouse.web.dto.AuditResults;
import net.mklew.hotelms.inhouse.web.dto.ReservationDto;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

/**
 * @author Marek Lewandowski
 * @since 5/26/13
 */
@Path("nightaudit")
public class NightauditResource
{

    private final HibernateSessionFactory hibernateSessionFactory;

    private final ReservationRepository reservationRepository;

    private final NoShowService noShowService;

    @Inject
    public NightauditResource(HibernateSessionFactory hibernateSessionFactory,
        ReservationRepository reservationRepository, NoShowService noShowService)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.reservationRepository = reservationRepository;
        this.noShowService = noShowService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response runNightAudit()
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        AuditResults auditResults = new AuditResults();

        Collection<Reservation> withCheckinStatus = checkForNoShows();

        Collection<Reservation> reservedForNextDay = markReservedForTomorrowAsCheckins();

        Function<Reservation, ReservationDto> toDto = new Function<Reservation, ReservationDto>()
            {
                @Override
                public ReservationDto apply(Reservation reservation)
                {
                    return ReservationDto.fromReservation(reservation);
                }
            };

        Collection<ReservationDto> tomorrowCheckIns = Collections2.transform(reservedForNextDay,
            toDto);

        auditResults.setTommorrowCheckIns(tomorrowCheckIns);

        Collection<ReservationDto> noShows = Collections2.transform(withCheckinStatus, toDto);
        auditResults.setNoShows(noShows);

        reservationRepository.updateAll(withCheckinStatus);
        reservationRepository.updateAll(reservedForNextDay);

        session.getTransaction().commit();

        return Response.ok(auditResults).build();
    }

    private Collection<Reservation> markReservedForTomorrowAsCheckins()
    {
        Collection<Reservation> reservedForNextDay = reservationRepository.findReservedForNextDay();
        for(Reservation reservation : reservedForNextDay)
        {
            reservation.changeToCheckIn();
        }
        return reservedForNextDay;
    }

    private Collection<Reservation> checkForNoShows()
    {
        Collection<Reservation> withCheckinStatus = reservationRepository
            .findWithStatus(ReservationStatus.CHECKIN);
        for(Reservation reservation : withCheckinStatus)
        {
            noShowService.markAsNoShow(reservation);
        }
        return withCheckinStatus;
    }
}
