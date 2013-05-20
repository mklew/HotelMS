package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.Interval;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:15 PM
 */
public interface Season extends AvailableIn, Activated
{
    AvailabilityPeriod getSeason();  // should have many availability periods
    String getSeasonName();
}
