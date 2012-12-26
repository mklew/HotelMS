package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.Room;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 4:23 PM
 */
public class ReservationFactoryImpl implements ReservationFactory
{
    public Reservation createSingleReservation(Guest reservationOwner, Room room, Rate rate, DateTime checkIn,
                                               DateTime checkOut,
                                               int numberOfAdults, int numberOfChildren, int extraBeds)
    {
        Validate.notNull(reservationOwner, "Reservation owner cannot be undefined");
        Validate.notNull(room, "Room cannot be undefined");
        Validate.notNull(checkIn, "CheckIn date cannot be undefined");
        Validate.notNull(checkOut, "CheckOut date cannot be undefined");
        Reservation reservation = new Reservation(Id.NO_ID, reservationOwner,
                room,
                rate, checkIn, checkOut, numberOfAdults, numberOfChildren, extraBeds, ReservationStatus.TECHNICAL);


        return reservation;
    }
}
