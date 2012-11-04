package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.money.Money;
import net.mklew.hotelms.domain.room.Room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class SeasonRate implements Rate
{

    private Money standardPrice;
    private Money upchargeExtraPerson;
    private Money upchargeExtraBed;
    private Season season;
    private Room room;

    public SeasonRate(Money standardPrice, Money upchargeExtraPerson, Money upchargeExtraBed, Season season, Room room)
    {
        this.standardPrice = standardPrice;
        this.upchargeExtraPerson = upchargeExtraPerson;
        this.upchargeExtraBed = upchargeExtraBed;
        this.season = season;
        this.room = room;
    }

    @Override
    public Money standardPrice()
    {
        return standardPrice;
    }

    @Override
    public Money upchargeExtraPerson()
    {
        return upchargeExtraPerson;
    }

    @Override
    public Money upchargeExtraBed()
    {
        return upchargeExtraBed;
    }

    @Override
    public String getRateName()
    {
       return season.getSeasonName();
    }
}
