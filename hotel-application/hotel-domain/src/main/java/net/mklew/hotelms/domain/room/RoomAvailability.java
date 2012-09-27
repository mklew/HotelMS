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
        public boolean isAvailable()
        {
            return true;
        }

    },
    OCCUPIED()
    {
        public boolean isAvailable()
        {
            return false;
        }
    },
    DNR()
    {
        public boolean isAvailable()
        {
            return false;
        }
    };


    public abstract boolean isAvailable();
}

