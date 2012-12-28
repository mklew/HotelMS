package net.mklew.hotelms.domain.booking.reservation;

import org.joda.time.DateTime;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 9:12 PM
 */
public class RoomIsUnavailableException extends Throwable
{
    private String roomName;
    private DateTime checkIn;
    private DateTime checkOut;

    public RoomIsUnavailableException(String roomName, DateTime checkIn, DateTime checkOut)
    {
        this.roomName = roomName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public DateTime getCheckIn()
    {
        return checkIn;
    }

    public DateTime getCheckOut()
    {
        return checkOut;
    }
}
