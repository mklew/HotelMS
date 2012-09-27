package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:44 AM
 */
public class Occupancy
{

    private final int standard;
    private final int maximum;

    public Occupancy(int standard, int maximum)
    {
        this.standard = standard;
        this.maximum = maximum;
    }

    public int getMaximumOccupancy()
    {
        return maximum;
    }

    public int getStandardOccupancy()
    {
        return standard;
    }
}
