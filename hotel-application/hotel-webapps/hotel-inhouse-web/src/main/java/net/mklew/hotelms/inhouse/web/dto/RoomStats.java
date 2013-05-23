package net.mklew.hotelms.inhouse.web.dto;

/**
 * @author Marek Lewandowski
 * @since 5/23/13
 */
public class RoomStats
{

    private int available;
    private int unavailable;

    public RoomStats()
    {
    }

    public RoomStats(int available, int unavailable)
    {
        this.available = available;
        this.unavailable = unavailable;
    }

    public int getAvailable()
    {
        return available;
    }

    public void setAvailable(int available)
    {
        this.available = available;
    }

    public int getUnavailable()
    {
        return unavailable;
    }

    public void setUnavailable(int unavailable)
    {
        this.unavailable = unavailable;
    }

}
