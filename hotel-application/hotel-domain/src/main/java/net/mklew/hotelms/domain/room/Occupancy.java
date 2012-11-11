package net.mklew.hotelms.domain.room;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Occupancy rhs = (Occupancy) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(standard, rhs.standard)
                .append(maximum, rhs.maximum)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(29, 71)
                .append(standard)
                .append(maximum)
                .toHashCode();
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
