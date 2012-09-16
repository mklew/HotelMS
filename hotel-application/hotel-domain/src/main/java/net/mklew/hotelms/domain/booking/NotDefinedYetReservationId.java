package net.mklew.hotelms.domain.booking;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 6:20 PM
 */

/**
 * Not defined yet reservation id is used prior to persisting reservation.
 */
public class NotDefinedYetReservationId extends ReservationId
{
    public NotDefinedYetReservationId(Group group)
    {
        super(group);
    }

    @Override
    public Id getId()
    {
        throw new IllegalStateException();
    }
}
