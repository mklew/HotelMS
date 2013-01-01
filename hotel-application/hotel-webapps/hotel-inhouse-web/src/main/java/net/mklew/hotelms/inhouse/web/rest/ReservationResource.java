package net.mklew.hotelms.inhouse.web.rest;

import com.google.common.base.Optional;
import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.reservation.*;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.booking.reservation.rates.RateRepository;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomNotFoundException;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.inhouse.web.dto.ErrorDto;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;
import net.mklew.hotelms.inhouse.web.dto.MissingGuestInformation;
import net.mklew.hotelms.inhouse.web.dto.ReservationDto;
import net.mklew.hotelms.inhouse.web.dto.dates.DateParser;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 3:46 PM
 */
@Singleton
@Path("/reservations")
public class ReservationResource
{
    private final Logger logger;
    private final ReservationFactory reservationFactory;
    private final GuestRepository guestRepository;
    private final HibernateSessionFactory hibernateSessionFactory;
    private final RoomRepository roomRepository;
    private final RateRepository rateRepository;
    private final BookingService bookingService;
    private final ReservationRepository reservationRepository;
    private final CheckInService checkInService;
    private final CheckOutService checkOutService;
    private final CancellationService cancellationService;

    public ReservationResource(Logger logger, ReservationFactory reservationFactory, GuestRepository guestRepository,
                               HibernateSessionFactory hibernateSessionFactory, RoomRepository roomRepository,
                               RateRepository rateRepository, BookingService bookingService,
                               ReservationRepository reservationRepository, CheckInService checkInService,
                               CheckOutService checkOutService, CancellationService cancellationService)
    {
        this.logger = logger;
        this.reservationFactory = reservationFactory;
        this.guestRepository = guestRepository;
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.roomRepository = roomRepository;
        this.rateRepository = rateRepository;
        this.bookingService = bookingService;
        this.reservationRepository = reservationRepository;
        this.checkInService = checkInService;
        this.checkOutService = checkOutService;
        this.cancellationService = cancellationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ReservationDto> getAllReservations()
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        final Collection<Reservation> reservations = reservationRepository.getAll();
        Collection<ReservationDto> dtos = new ArrayList<>(reservations.size());
        for (Reservation reservation : reservations)
        {
            dtos.add(ReservationDto.fromReservation(reservation));
        }
        session.getTransaction().commit();
        return dtos;
    }

    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") String reservationId)
    {
        try
        {
            Id id = Id.of(reservationId);
            Session session = hibernateSessionFactory.getCurrentSession();
            session.beginTransaction();
            final Optional<Reservation> reservationOptional = reservationRepository.lookup(id);
            if (reservationOptional.isPresent())
            {
                final ReservationDto dto = ReservationDto.fromReservation(reservationOptional.get());
                session.getTransaction().commit();
                return Response.ok(dto, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.OK).build();
            }
            else
            {
                session.getTransaction().commit();
                return Response.ok().status(Response.Status.NOT_FOUND).build();
            }

        }
        catch (Exception e)
        {
            throw e;
            //return Response.ok(e.getMessage()).build();
        }
    }

    private Response reservationOperationTemplate(String reservationId, ReservationOperationAction action)
    {
        Id id = Id.of(reservationId);
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        final Optional<Reservation> reservationOptional = reservationRepository.lookup(id);
        if (reservationOptional.isPresent())
        {
            final Reservation reservation = reservationOptional.get();
            return action.doAction(reservation);
        }
        else
        {
            session.getTransaction().commit();
            return Response.ok(new ErrorDto("Reservation with id " + reservationId + " has not been found.",
                    "RESERVATION-NOT-FOUND"), MediaType.APPLICATION_JSON_TYPE).status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    private static abstract class ReservationOperationAction
    {
        abstract Response doAction(final Reservation reservation);
    }

    @Path("/{id}/checkIn")
    @POST
    public Response checkInReservation(@PathParam("id") String reservationId)
    {
        return reservationOperationTemplate(reservationId, new ReservationOperationAction()
        {
            @Override
            Response doAction(Reservation reservation)
            {
                checkInService.checkIn(reservation);
                final Session session = hibernateSessionFactory.getCurrentSession();
                session.saveOrUpdate(reservation);
                session.getTransaction().commit();
                // place for error handling depending on business conditions and returning different responses.
                return Response.ok().status(Response.Status.OK).build();
            }
        });
    }

    @Path("/{id}/checkOut")
    @POST
    public Response checkOutReservation(@PathParam("id") String reservationId)
    {
        return reservationOperationTemplate(reservationId, new ReservationOperationAction()
        {
            @Override
            Response doAction(Reservation reservation)
            {
                checkOutService.checkOut(reservation);
                final Session session = hibernateSessionFactory.getCurrentSession();
                session.saveOrUpdate(reservation);
                session.getTransaction().commit();
                // place for error handling depending on business conditions and returning different responses.
                return Response.ok().status(Response.Status.OK).build();
            }
        });
    }

    @Path("/{id}/cancel")
    @POST
    public Response cancelReservation(@PathParam("id") String reservationId)
    {
        return reservationOperationTemplate(reservationId, new ReservationOperationAction()
        {
            @Override
            Response doAction(Reservation reservation)
            {
                cancellationService.cancel(reservation);
                final Session session = hibernateSessionFactory.getCurrentSession();
                session.saveOrUpdate(reservation);
                session.getTransaction().commit();
                // place for error handling depending on business conditions and returning different responses.
                return Response.ok().status(Response.Status.OK).build();
            }
        });
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewReservation(MultivaluedMap<String, String> formParams,
                                         @Context HttpServletResponse httpServletResponse)
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.debug("Got new reservation with parameters: " + formParams.toString());
        try
        {
            GuestDto reservationOwner = GuestDto.fromReservationForm(formParams);
            ReservationDto reservationDto = ReservationDto.fromReservationForm(formParams);

            Guest owner;
            if (reservationOwner.exists())
            {
                owner = guestRepository.findGuestById(Long.parseLong(reservationOwner.id));
            }
            else
            {
                owner = new Guest(reservationOwner.socialTitle, reservationOwner.firstName,
                        reservationOwner.surname, reservationOwner.gender, reservationOwner.idType,
                        reservationOwner.idNumber, reservationOwner.phoneNumber);
                owner.setPreferences(reservationOwner.preferences);
                owner.setDateOfBirth(reservationOwner.dateOfBirthDate);
                // owner.setEmailAddress(); // todo add field to form, dto, and set it here
                // todo nationality, address and other
                guestRepository.saveGuest(owner);
            }

            // get room
            RoomName roomName = RoomName.getNameWithoutPrefix(reservationDto.getRoomName());
            final Room room = roomRepository.getRoomByName(roomName);
            // find rate
            Collection<Rate> rates = rateRepository.getAllRatesForRoom(room);
            Rate rate = getChosenRate(reservationDto, rates);

            // create reservation using factory
            Reservation reservation;
            if (ReservationType.fromName(reservationDto.getReservationType()).equals(ReservationType.SINGLE))
            {
                reservation = reservationFactory.createSingleReservation(owner, room, rate,
                        reservationDto.getCheckinDate(),
                        reservationDto.getCheckoutDate(), Integer.parseInt(reservationDto.getNumberOfAdults()),
                        Integer.parseInt(reservationDto.getNumberOfChildren()), Integer.parseInt(reservationDto
                        .getRoomExtraBed()));
            }
            else
            {
                throw new OperationNotSupportedException("Other reservation types are not supported ");
            }

            // book reservation or fail on exception
            bookingService.bookReservation(reservation);
            ReservationDto bookedDto = ReservationDto.fromReservation(reservation);

            session.getTransaction().commit();

            return Response.ok(bookedDto, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.CREATED).build();
        }
        catch (MissingGuestInformation missingGuestInformation)
        {
            logger.error("Reservation owner has no sufficient information", missingGuestInformation);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Response.ok(new ErrorDto("Missing guest information", "GUEST-MISSING-INFO"),
                    MediaType.APPLICATION_JSON_TYPE).status
                    (Response.Status.BAD_REQUEST).build();
        }
        catch (RoomNotFoundException e)
        {
            logger.error("Room not found exception", e);
            return Response.ok(new ErrorDto("Room not found", "ROOM-NOT-FOUND"), MediaType.APPLICATION_JSON_TYPE).status
                    (Response.Status.NOT_FOUND).build();
        }
        catch (OperationNotSupportedException e)
        {
            logger.error("Operation not supported", e);
            return Response.ok(new ErrorDto("Operation not supported", "OPERATION-NOT-SUPPORTED"),
                    MediaType.APPLICATION_JSON_TYPE).status
                    (HttpServletResponse.SC_NOT_IMPLEMENTED).build();
        }
        catch (RoomIsUnavailableException e)
        {
            final String message = "Room " + e.getRoomName() + " is unavailable between " +
                    DateParser.fromDate(e.getCheckIn()) + " and " + DateParser.fromDate(e.getCheckOut());
            return Response.ok(new ErrorDto(message, "ROOM-UNAVAILABLE"), MediaType.APPLICATION_JSON_TYPE).status
                    (Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}/")
    public Response deleteReservation(@PathParam("id") String reservationId)
    {
        Id id = Id.of(reservationId);
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        final Optional<Reservation> reservationOptional = reservationRepository.lookup(id);
        if (reservationOptional.isPresent())
        {
            final Reservation reservation = reservationOptional.get();
            reservationRepository.deleteReservation(reservation);
            session.getTransaction().commit();
            return Response.ok().status(HttpServletResponse.SC_OK).build();
        }
        else
        {
            session.getTransaction().commit();
            return Response.ok(new ErrorDto("Reservation with id " + reservationId + " has not been found.",
                    "RESERVATION-NOT-FOUND"), MediaType.APPLICATION_JSON_TYPE).status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyReservation(@PathParam("id") String reservationId, final ReservationDto reservationParam)
    {
        // TODO make it DRY
        return reservationOperationTemplate(reservationId, new ReservationOperationAction()
        {
            @Override
            Response doAction(Reservation reservation)
            {
                reservationParam.validateRequired();
                reservationParam.init();
                final ReservationDto reservationDto = reservationParam;
                // Only few things can be changed in edit. Other properties must be changed in a different way
                if (reservationDto.getCheckinDate().isAfter(reservationDto.getCheckoutDate()))
                {
                    hibernateSessionFactory.getCurrentSession().getTransaction().rollback();
                    return Response.ok(new ErrorDto("CheckIn " + reservationDto.getCheckin() + " date cannot be after" +
                            " CheckOut date " + reservationDto.getCheckout(), "RESERVATION-WRONG-DATES"),
                            MediaType.APPLICATION_JSON_TYPE).status(HttpServletResponse.SC_FORBIDDEN).build();
                }
                if (!(reservation.getCheckIn().equals(reservationDto.getCheckinDate())))
                {
                    checkInService.changeCheckInDate(reservation, reservationDto.getCheckinDate());
                }
                if (!(reservation.getCheckOut().equals(reservationDto.getCheckoutDate())))
                {
                    checkOutService.changeCheckOutDate(reservation, reservationDto.getCheckoutDate());
                }
                int extraBeds = Integer.valueOf(reservationDto.getRoomExtraBed());
                if (reservation.getExtraBeds() != extraBeds)
                {
                    reservation.setExtraBeds(extraBeds);
                }

                int numberOfAdults = Integer.valueOf(reservationDto.getNumberOfAdults());
                if (reservation.getNumberOfAdults() != numberOfAdults)
                {
                    reservation.setNumberOfAdults(numberOfAdults);
                }

                int numberOfChildren = Integer.valueOf(reservationDto.getNumberOfChildren());
                if (reservation.getNumberOfChildren() != numberOfChildren)
                {
                    reservation.setNumberOfChildren(numberOfChildren);
                }
                reservationRepository.update(reservation);
                hibernateSessionFactory.getCurrentSession().getTransaction().commit();
                return Response.ok().build();
            }
        });
    }

    @PUT
    @Path("/{id}/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response modifyReservation(@PathParam("id") String reservationId, final MultivaluedMap<String,
            String> formParams)
    {
        return reservationOperationTemplate(reservationId, new ReservationOperationAction()
        {
            @Override
            Response doAction(Reservation reservation)
            {
                final ReservationDto reservationDto = ReservationDto.fromReservationForm(formParams);
                // Only few things can be changed in edit. Other properties must be changed in a different way
                if (reservationDto.getCheckinDate().isAfter(reservationDto.getCheckoutDate()))
                {
                    hibernateSessionFactory.getCurrentSession().getTransaction().rollback();
                    return Response.ok(new ErrorDto("CheckIn " + reservationDto.getCheckin() + " date cannot be after" +
                            " CheckOut date " + reservationDto.getCheckout(), "RESERVATION-WRONG-DATES"),
                            MediaType.APPLICATION_JSON_TYPE).status(HttpServletResponse.SC_FORBIDDEN).build();
                }
                if (!(reservation.getCheckIn().equals(reservationDto.getCheckinDate())))
                {
                    checkInService.changeCheckInDate(reservation, reservationDto.getCheckinDate());
                }
                if (!(reservation.getCheckOut().equals(reservationDto.getCheckoutDate())))
                {
                    checkOutService.changeCheckOutDate(reservation, reservationDto.getCheckoutDate());
                }
                int extraBeds = Integer.valueOf(reservationDto.getRoomExtraBed());
                if (reservation.getExtraBeds() != extraBeds)
                {
                    reservation.setExtraBeds(extraBeds);
                }

                int numberOfAdults = Integer.valueOf(reservationDto.getNumberOfAdults());
                if (reservation.getNumberOfAdults() != numberOfAdults)
                {
                    reservation.setNumberOfAdults(numberOfAdults);
                }

                int numberOfChildren = Integer.valueOf(reservationDto.getNumberOfChildren());
                if (reservation.getNumberOfChildren() != numberOfChildren)
                {
                    reservation.setNumberOfChildren(numberOfChildren);
                }
                reservationRepository.update(reservation);
                hibernateSessionFactory.getCurrentSession().getTransaction().commit();
                return Response.ok().build();
            }
        });
    }

    private Rate getChosenRate(ReservationDto reservationDto, Collection<Rate> rates)
    {
        for (Rate rate : rates)
        {
            if (reservationDto.getRateType().equals(rate.getRateName()))
            {
                return rate;
            }
        }
        throw new RuntimeException("Rate not found");
    }
}
