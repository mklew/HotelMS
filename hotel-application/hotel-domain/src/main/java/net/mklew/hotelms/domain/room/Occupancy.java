package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:44 AM
 */
public class Occupancy
{

    private int standard;
    private int maximum;

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

    // hibernate
    private void setStandard(int standard)
    {
        this.standard = standard;
    }

    // hibernate
    private void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }

    // hibernate
    int getStandard()
    {
        return standard;
    }

    // hibernate
    int getMaximum()
    {
        return maximum;
    }

    Occupancy()
    {
        // hibernate
    }
}
