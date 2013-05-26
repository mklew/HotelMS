package net.mklew.hotelms.persistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.domain.booking.reservation.ReservationRepository;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;

import com.google.common.base.Optional;
import org.joda.time.base.BaseDateTime;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12 time 9:16 PM
 */
public class ReservationRepositoryHibernate
    extends HibernateRepository
    implements ReservationRepository
{
    private static final String FIND_ALL_RESERVATIONS_FOR_ROOM_AROUND_DATES_QUERY = "select reservation from "
        + "Reservation "
        + "reservation join reservation.nights as night where night.date in (:dates) group by reservation";

    private static final String FOR_NEXT_DAY = "select reservation from " + "Reservation "
        + "reservation join reservation.nights as night where night.date in (:dates) and "
        + "reservation.reservationStatus = :status group by reservation";

    public ReservationRepositoryHibernate(HibernateSessionFactory hibernateSessionFactory)
    {
        super(hibernateSessionFactory);
    }

    @Override
    public Collection<Reservation> findAllReservationsAroundDates(DateTime checkIn,
        DateTime checkOut)
    {
        final Session session = getCurrentSession();
        Collection<DateTime> dates = new ArrayList<>();
        for(int i = 0; !checkIn.plusDays(i).equals(checkOut.plusDays(1)); ++i)
        {
            dates.add(checkIn.plusDays(i));
        }
        @SuppressWarnings("unchecked")
        List<Reservation> reservations = (List<Reservation>)session
            .createQuery(FIND_ALL_RESERVATIONS_FOR_ROOM_AROUND_DATES_QUERY)
            .setParameterList("dates", dates).list();
        return reservations;
    }

    @Override
    public Collection<Reservation> findReservedForNextDay()
    {
        final Session session = getCurrentSession();

        final List<DateTime> tomorrow = Arrays.asList(DateTime.now().toDateMidnight().plusDays(1).toDateTime());
        @SuppressWarnings("unchecked")
        List<Reservation> reservations = (List<Reservation>)session.createQuery(FOR_NEXT_DAY)
            .setParameterList("dates", tomorrow).setParameter("status", ReservationStatus.RESERVED)
            .list();
        return reservations;
    }

    @Override
    public void bookNewReservation(Reservation reservation)
    {
        final Session session = getCurrentSession();
        session.save(reservation);
    }

    @Override
    public Collection<Reservation> getAll()
    {
        final Session session = getCurrentSession();
        final List reservations = session.createQuery("from Reservation").list();
        return (Collection<Reservation>)reservations;
    }

    @Override
    public Optional<Reservation> lookup(Id id)
    {
        final Session session = getCurrentSession();
        Reservation reservation = (Reservation)session.get(Reservation.class, id);
        if(reservation != null)
        {
            return Optional.of(reservation);
        }
        else
        {
            return Optional.absent();
        }
    }

    @Override
    public Collection<Reservation> findWithStatus(ReservationStatus status)
    {
        final Session session = getCurrentSession();
        List<Reservation> reservationsWithStatus = session
            .createQuery("from Reservation r where r.reservationStatus = :status")
            .setParameter("status", status).list();
        return reservationsWithStatus;
    }

    @Override
    public void deleteReservation(Reservation reservation)
    {
        final Session session = getCurrentSession();
        session.delete(reservation);
    }

    @Override
    public void update(Reservation reservation)
    {
        final Session session = getCurrentSession();
        session.update(reservation);
    }

    @Override
    public void updateAll(Collection<Reservation> reservations)
    {
        final Session session = getCurrentSession();
        for(Reservation reservation : reservations)
        {
            session.update(reservation);
        }
    }
}
