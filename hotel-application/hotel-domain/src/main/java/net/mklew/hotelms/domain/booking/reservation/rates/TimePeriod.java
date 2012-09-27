package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class TimePeriod  implements Season
{
    private String name;
    private int sequence;
    private DateTime from;
    private DateTime to;
    private boolean isActive;

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
}
