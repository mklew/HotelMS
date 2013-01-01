package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 1/1/13
 *        time 8:45 PM
 */
public class RateNotFoundException extends Throwable
{
    private Rate rate;

    public RateNotFoundException(Rate rate)
    {

    }
}
