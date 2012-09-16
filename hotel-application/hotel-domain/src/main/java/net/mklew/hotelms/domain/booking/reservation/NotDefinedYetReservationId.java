package net.mklew.hotelms.domain.booking.reservation;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 6:20 PM
 */

import net.mklew.hotelms.domain.booking.Id;

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
