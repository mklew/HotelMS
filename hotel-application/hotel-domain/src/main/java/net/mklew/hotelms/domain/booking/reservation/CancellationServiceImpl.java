package net.mklew.hotelms.domain.booking.reservation;

/**
 * Cancellation of reservation might require some logic depending on reservation.
 * Either some fine should be charged or not depending on business logic
 * <p/>
 * This is the place for that business logic. Current implementation just changes data.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/28/12
 *        time 10:18 PM
 */
public class CancellationServiceImpl implements CancellationService
{
    @Override
    public void cancel(Reservation reservation)
    {
        reservation.cancel();
    }
}
