package net.mklew.hotelms.domain.booking.reservation.rates;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Represents time period when something is available.
 * For example there could be a special weekend package available in every weekend in October or
 * it might be season rate which is available only during some season.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 2:14 PM
 *
 * @see Package
 * @see Season
 */
public class AvailabilityPeriod implements AvailableIn
{
    private Long id; // hibernate

    private DateTime from;
    private DateTime to;
    private boolean active;

    public AvailabilityPeriod(DateTime from, DateTime to, boolean active)
    {
        this.from = from;
        this.to = to;
        this.active = active;
    }

    @Override
    public boolean isAvailableIn(DateTime time)
    {
        return new Interval(from, to).contains(time);
    }

    @Override
    public boolean isAvailableNow()
    {
        return isAvailableIn(DateTime.now());
    }

    @Override
    public boolean isActive()
    {
        return active;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        AvailabilityPeriod rhs = (AvailabilityPeriod) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(from, rhs.from)
                .append(to, rhs.to)
                .append(active, rhs.active)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(23, 67)
                .append(from)
                .append(to)
                .append(active)
                .toHashCode();
    }

    // hibernate

    // private for hibernate
    private Long getId()
    {
        return id;
    }

    // private setters for Hibernate
    private void setId(Long id)
    {
        this.id = id;
    }

    AvailabilityPeriod()
    {
        // for hibernate
        from = null;
        to = null;
    }

    DateTime getFrom()
    {
        return from;
    }

    DateTime getTo()
    {
        return to;
    }

    private void setFrom(DateTime from)
    {
        this.from = from;
    }

    private void setTo(DateTime to)
    {
        this.to = to;
    }

    private void setActive(boolean active)
    {
        this.active = active;
    }
}
