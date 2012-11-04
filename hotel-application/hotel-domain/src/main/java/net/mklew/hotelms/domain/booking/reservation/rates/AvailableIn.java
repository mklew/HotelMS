package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;

/**
 * Provides method to check if given object is available at specified time
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:20 PM
 */
public interface AvailableIn extends Activated
{
    boolean isAvailableIn(DateTime time);
    boolean isAvailableNow();

}
