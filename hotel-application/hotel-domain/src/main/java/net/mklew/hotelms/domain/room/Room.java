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
    private Integer maxExtraBeds;

    public Room(RoomType type, RoomName name, Integer maxExtraBeds)
    {
        this.type = type;
        this.name = name;
        this.maxExtraBeds = maxExtraBeds;
    }

    private Occupancy occupancy;

    public boolean isAvailable()
    {
        return availability.isAvailable();
    }

    public String fullRoomName()
    {
        return name.getRoomName();
    }

    public String roomTypeName()
    {
        return type.getTypeName();
    }

    public Integer maxExtraBeds()
    {
        return maxExtraBeds;
    }
}
