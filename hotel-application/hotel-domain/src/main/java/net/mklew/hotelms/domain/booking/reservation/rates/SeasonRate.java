package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.money.Money;
import net.mklew.hotelms.domain.room.Room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:59 AM
 */
public class SeasonRate extends Rate
{
    private Season season;

    public SeasonRate(Money standardPrice, Money upchargeExtraPerson, Money upchargeExtraBed, Room room, Season season)
    {
        super(standardPrice, upchargeExtraPerson, upchargeExtraBed, room);
        this.season = season;
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

    public Season getSeason()
    {
        return season;
    }

    // hibernate
    private void setSeason(Season season)
    {
        this.season = season;
    }

    // hibernate
    SeasonRate()
    {
        // hibernate
    }
}
