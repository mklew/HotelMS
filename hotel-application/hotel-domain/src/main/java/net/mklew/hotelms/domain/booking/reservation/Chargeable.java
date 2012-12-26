package net.mklew.hotelms.domain.booking.reservation;

import org.joda.money.Money;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/26/12
 *        time 9:15 PM
 */
public interface Chargeable
{
    Money charge();
}
