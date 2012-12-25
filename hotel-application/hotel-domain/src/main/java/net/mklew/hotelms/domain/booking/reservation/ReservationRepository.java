package net.mklew.hotelms.domain.booking.reservation;

import org.joda.time.DateTime;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 9:14 PM
 */
public interface ReservationRepository
{
    /**
     * Returns reservations which overlap given interval
     *
     * @param checkIn
     * @param checkOut
     * @return
     */
    Collection<Reservation> findAllReservationsAroundDates(DateTime checkIn, DateTime checkOut);

    void bookNewReservation(Reservation reservation);
}
