package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.Room;
import org.joda.time.DateTime;

/**
 * Factory for creating reservations of different types.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 4:44 PM
 */
public interface ReservationFactory
{
    public Reservation createSingleReservation(Guest reservationOwner, Room room, Rate rate, DateTime checkIn,
                                               DateTime checkOut,
                                               int numberOfAdults, int numberOfChildren, int extraBeds);
}
