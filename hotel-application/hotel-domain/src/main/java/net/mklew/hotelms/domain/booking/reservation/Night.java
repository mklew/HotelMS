package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.money.Discount;
import net.mklew.hotelms.domain.money.Money;
import org.joda.time.DateTime;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class Night
{
    private final DateTime date;
    private boolean isUsed;
    private Money price;
    private Discount discount;

    public Night(DateTime date, boolean used, Money price, Discount discount)
    {
        this.date = date;
        isUsed = used;
        this.price = price;
        this.discount = discount;
    }
}
