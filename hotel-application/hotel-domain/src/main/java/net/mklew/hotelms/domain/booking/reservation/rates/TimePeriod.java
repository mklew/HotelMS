package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */

// todo instead of mergeing two concepts into one, create Season class and AvailabilityPeriod class where SeasonClass is used by SeasonRate and later by Package
public class TimePeriod  implements Season, AvailabilityPeriod
{
    private Long id; // hibernate

    private String name;
    private DateTime from;
    private DateTime to;
    private boolean isActive;

    // todo add mapping
    //private Package package;
    //private Rate rate;

    // todo after split into Season and Availability period, remove constructor and create factory methods


    public TimePeriod(String name, DateTime from, DateTime to, boolean active)
    {
        this.name = name;
        this.from = from;
        this.to = to;
        isActive = active;
    }

    @Override
    public Interval getSeason()
    {
        //return new Interval(from, to);
        return new Interval(DateTime.now(), DateTime.now());
    }

    @Override
    public boolean isAvailableIn(DateTime time)
    {
//        Interval interval = new Interval(from, to);
//        return interval.contains(time);
        return true;
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

    public String getName()
    {
        return name;
    }

    public DateTime getFrom()
    {
        return from;
    }

    public DateTime getTo()
    {
        return to;
    }

    public boolean getIsActive()
    {
        return isActive;
    }

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

    private void setIsActive(boolean active)
    {
        isActive = active;
    }

    public TimePeriod()
    {
        // for hibernate
    }
}
