package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.room.Room;
import org.joda.money.Money;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:22 AM
 */
public abstract class Rate
{
    protected Money standardPrice;
    protected Money upchargeExtraPerson;
    protected Money upchargeExtraBed;
    protected Room room;

    public Rate(Money upchargeExtraPerson, Money standardPrice, Money upchargeExtraBed, Room room)
    {
        this.upchargeExtraPerson = upchargeExtraPerson;
        this.standardPrice = standardPrice;
        this.upchargeExtraBed = upchargeExtraBed;
    }

    public abstract String getRateName();

    public Money standardPrice()
    {
        return standardPrice;
    }

    public Money upchargeExtraPerson()
    {
        return upchargeExtraPerson;
    }

    public Money upchargeExtraBed()
    {
        return upchargeExtraBed;
    }

    public Room getRoom()
    {
        return room;
    }
}
