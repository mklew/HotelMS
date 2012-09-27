package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class TimePeriod
{
    private String name;
    private DateTime from;
    private DateTime to;
    private boolean isActive;

    public boolean isAvailableIn(DateTime time)
    {

        Interval interval = new Interval(from, to);
        return interval.contains(time);
    }

    public boolean isAvailableIn()
    {
        return this.isAvailableIn(DateTime.now());
    }
}
