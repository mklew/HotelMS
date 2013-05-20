package net.mklew.hotelms.domain.booking.reservation;

/**
 * CheckIn process might involve some kind of business logic. This is place for it.
 * <p/>
 * Current implementation has no business logic.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/28/12
 *        time 10:11 PM
 */
public class CheckInServiceImpl implements CheckInService
{

    @Override
    public void checkIn(Reservation reservation)
    {
        reservation.checkIn();
    }
}
