package net.mklew.hotelms.domain.booking.reservation;

/**
 * Marks reservation as no show and performs business logic related to no shows.
 *
 * @author Marek Lewandowski
 * @since 5/26/13
 */
public interface NoShowService
{
    public void markAsNoShow(Reservation reservation);
}
