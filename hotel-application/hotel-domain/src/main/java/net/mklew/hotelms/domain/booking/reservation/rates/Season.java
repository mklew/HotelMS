package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:15 PM
 */
public interface Season
{
    Interval getSeason();
    boolean isAvailableIn(DateTime time);
    boolean isAvailableNow();
    String getSeasonName();
}
