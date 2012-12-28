package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.room.Room;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.money.Money;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:22 AM
 */
public abstract class Rate
{
    protected Long id; // hibernate
    protected Money standardPrice;
    protected Money upchargeExtraPerson;
    protected Money upchargeExtraBed;
    protected Room room;

    public Rate(Money standardPrice, Money upchargeExtraPerson, Money upchargeExtraBed, Room room)
    {
        this.standardPrice = standardPrice;
        this.upchargeExtraPerson = upchargeExtraPerson;
        this.upchargeExtraBed = upchargeExtraBed;
        this.room = room;
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

    public void setRoom(Room room)
    {
        this.room = room;
    }

    // hibernate
    protected void setStandardPrice(Money standardPrice)
    {
        this.standardPrice = standardPrice;
    }

    // hibernate
    protected void setUpchargeExtraPerson(Money upchargeExtraPerson)
    {
        this.upchargeExtraPerson = upchargeExtraPerson;
    }

    // hibernate
    protected void setUpchargeExtraBed(Money upchargeExtraBed)
    {
        this.upchargeExtraBed = upchargeExtraBed;
    }

    // hibernate
    protected Money getStandardPrice()
    {
        return standardPrice;
    }

    // hibernate
    protected Money getUpchargeExtraPerson()
    {
        return upchargeExtraPerson;
    }

    // hibernate
    protected Money getUpchargeExtraBed()
    {
        return upchargeExtraBed;
    }

    protected Long getId()
    {
        return id;
    }

    protected void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (obj.getClass() != getClass())
        {
            return false;
        }
        Rate rhs = (Rate) obj;
        return new EqualsBuilder()
                .append(standardPrice, rhs.standardPrice)
                .append(upchargeExtraPerson, rhs.upchargeExtraPerson)
                .append(upchargeExtraBed, rhs.upchargeExtraBed)
                .append(getRoom(), rhs.getRoom())
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 53)
                .append(standardPrice)
                .append(upchargeExtraPerson)
                .append(upchargeExtraBed)
                .append(getRoom())
                .toHashCode();
    }

    // hibernate
    Rate()
    {
        // hibernate
    }
}
