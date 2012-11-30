package net.mklew.hotelms.domain.booking;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * This is class where complex business logic has its place.
 * Current implementation abstracts from complexity.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 4:13 PM
 */
public class ChargeCalculatorService
{
    // TODO change this class into pico managed singleton -> no static methods

    public static Money calculateChargeForStay(Rate rate, DateTime checkin, DateTime checkout)
    {
        final long lengthOfStay = getLengthOfStay(checkin, checkout);
        return rate.standardPrice().multipliedBy(lengthOfStay);
    }

    private static long getLengthOfStay(DateTime checkin, DateTime checkout)
    {
        return new Duration(checkin, checkout).getStandardDays() + 1;
    }
}
