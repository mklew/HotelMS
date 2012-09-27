package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.money.Money;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:22 AM
 */
public interface Rate
{
    Money standardPrice();
    Money upchargeExtraPerson();
    Money upchargeExtraBed();
}
