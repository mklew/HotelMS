package net.mklew.hotelms.domain.room;

import net.mklew.hotelms.domain.booking.reservation.rates.RackRate;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:27 AM
 */
public class Room
{
    private RackRate rackRate;
    private HousekeepingStatus status;
    private RoomAvailability availability;
    private RoomType type;
    private RoomName name;
    private Occupancy occupancy;

    public boolean isAvailable()
    {
        return availability.isAvailable();
    }

}
