package net.mklew.hotelms.inhouse.web.rest;

import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.domain.booking.reservation.ReservationFactory;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;
import net.mklew.hotelms.inhouse.web.dto.MissingGuestInformation;
import net.mklew.hotelms.inhouse.web.dto.ReservationDto;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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

    public ReservationResource(Logger logger, ReservationFactory reservationFactory, GuestRepository guestRepository,
                               HibernateSessionFactory hibernateSessionFactory, RoomRepository roomRepository)
    {
        this.logger = logger;
        this.reservationFactory = reservationFactory;
        this.guestRepository = guestRepository;
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.roomRepository = roomRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ReservationDto> getAllReservations()
    {
        // todo
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ReservationDto createNewReservation(MultivaluedMap<String, String> formParams)
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.debug("Got new reservation with parameters: " + formParams.toString());
        try
        {
            GuestDto reservationOwner = GuestDto.fromReservationForm(formParams);
            ReservationDto reservationDto = ReservationDto.fromReservationForm(formParams);

            Guest owner = null;
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
            //roomRepository.getRoomByName(roomName);
            // get rate

            // create reservation using factory

            // validate that reservation can be booked - room is available at that time

            // persist reservation

            // return persisted reservation dto

            session.getTransaction().commit();
            // return dto;
        }
        catch (MissingGuestInformation missingGuestInformation)
        {
            logger.error("Reservation owner has no sufficient information", missingGuestInformation);
            return null;
        }
        return null;
    }
}
