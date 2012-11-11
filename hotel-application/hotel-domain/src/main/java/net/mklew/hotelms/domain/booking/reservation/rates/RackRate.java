package net.mklew.hotelms.domain.booking.reservation.rates;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.money.Money;
import net.mklew.hotelms.domain.room.Room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:24 AM
 */
public class RackRate extends Rate
{
    public RackRate(Money standardPrice, Money upchargeExtraPerson, Money upchargeExtraBed, Room room)
    {
        super(standardPrice, upchargeExtraPerson, upchargeExtraBed, room);
    }

    @Override
    public String getRateName()
    {
        return "Rack rate";
    }

    RackRate()
    {
        // hibernate
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        RackRate rhs = (RackRate) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}
