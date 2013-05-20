package net.mklew.hotelms.domain.booking.reservation;

/**
 * CheckingOut reservation might involve complex business logic.
 * <p/>
 * Example of business rule:
 * check if its not early checkout and do it right according to policy about early checkouts,
 * <p/>
 * This is place for it.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/28/12
 *        time 10:14 PM
 */
public class CheckOutServiceImpl implements CheckOutService
{
    @Override
    public void checkOut(Reservation reservation)
    {
        reservation.checkOut();
    }
}
