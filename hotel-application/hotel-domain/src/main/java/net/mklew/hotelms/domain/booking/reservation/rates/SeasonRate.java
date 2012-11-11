package net.mklew.hotelms.domain.booking.reservation.rates;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SeasonRate rhs = (SeasonRate) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(season, rhs.season)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(43, 89)
                .append(season)
                .toHashCode();
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
