package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class TimePeriod  implements Season, AvailabilityPeriod
{
    private long id; // hibernate

    private String name;
    private DateTime from;
    private DateTime to;
    private boolean isActive;

    // todo add mapping
    //private Package package;
    //private Rate rate;

    @Override
    public Interval getSeason()
    {
        return new Interval(from, to);
    }

    @Override
    public boolean isAvailableIn(DateTime time)
    {

        Interval interval = new Interval(from, to);
        return interval.contains(time);
    }

    @Override
    public boolean isAvailableNow()
    {
        return this.isAvailableIn(DateTime.now());
    }

    @Override
    public String getSeasonName()
    {
        return name;
    }

    // private for hibernate
    private long getId()
    {
        return id;
    }
    // private setters for Hibernate
    private void setId(long id)
    {
        this.id = id;
    }

    private void setName(String name)
    {
        this.name = name;
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
        isActive = active;
    }

    TimePeriod()
    {
        // for hibernate
    }
}
