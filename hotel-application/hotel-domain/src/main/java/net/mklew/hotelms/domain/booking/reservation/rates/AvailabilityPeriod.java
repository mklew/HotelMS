package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:20 PM
 */
public interface AvailabilityPeriod
{
    boolean isAvailableIn(DateTime time);
    boolean isAvailableNow();

}
