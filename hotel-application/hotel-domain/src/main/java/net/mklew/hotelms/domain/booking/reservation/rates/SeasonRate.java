package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.money.Money;
import net.mklew.hotelms.domain.room.Room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class SeasonRate implements Rate
{

    private Season season;
    private Room room;

    @Override
    public Money standardPrice()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Money upchargeExtraPerson()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Money upchargeExtraBed()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
