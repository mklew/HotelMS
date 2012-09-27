package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:34 AM
 */
public enum RoomAvailability
{
    AVAILABLE()
    {
        boolean isAvailable()
        {
            return true;
        }

    },
    OCCUPIED()
    {
        boolean isAvailable()
        {
            return false;
        }
    };


    abstract boolean isAvailable();
}

