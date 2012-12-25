package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 4:10 PM
 */
public class RoomNotFoundException extends Exception
{
    public RoomNotFoundException(String message)
    {
        super(message);
    }
}
