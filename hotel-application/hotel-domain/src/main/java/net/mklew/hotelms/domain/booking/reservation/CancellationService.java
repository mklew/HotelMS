package net.mklew.hotelms.domain.booking.reservation;

/**
 * @author Marek Lewandowski
 * @since 5/20/13
 */
public interface CancellationService
{
    void cancel(Reservation reservation);
}
