package net.mklew.hotelms.domain.room;

import net.mklew.hotelms.domain.booking.reservation.rates.RackRate;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.money.Money;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:27 AM
 */
public class Room
{
    private String prefix; // work around Hibernate limits, if id is composite-id then
    // all properties of that composite-id class must be used otherwise strange things happen
    private RoomName name;
    private RoomType type;
    private Set<Rate> rates;
    private HousekeepingStatus housekeepingStatus;
    private RoomAvailability availability;
    private Integer maxExtraBeds;
    private Occupancy occupancy;

    public Room(String prefix, RoomName name, RoomType type, HousekeepingStatus housekeepingStatus,
                RoomAvailability availability, Integer maxExtraBeds, Occupancy occupancy, Money standardPrice, Money
            upchargeExtraPerson, Money upchargeExtraBed)
    {
        this.prefix = prefix;
        this.name = name;
        this.type = type;
        this.housekeepingStatus = housekeepingStatus;
        this.availability = availability;
        this.maxExtraBeds = maxExtraBeds;
        this.occupancy = occupancy;
        this.rates = new HashSet<>();
        final RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, this);
        rates.add(rackRate);
    }

    public boolean isAvailable()
    {
        return availability.isAvailable();
    }

    public String fullRoomName()
    {
        return prefix + name.getRoomName();
    }

    public String roomNumber()
    {
        return name.getRoomNumber();
    }

    public String roomTypeName()
    {
        return type.getTypeName();
    }

    public Integer maxExtraBeds()
    {
        return maxExtraBeds;
    }

    public Rate rackRate()
    {
        for (Rate rate : rates)
        {
            if (rate.getClass().equals(RackRate.class))
            {
                return rate;
            }
        }
        return null;
    }

    // hibernate


    public RoomName getName()
    {
        return name;
    }

    private void setName(RoomName name)
    {
        this.name = name;
    }

    private RoomType getType()
    {
        return type;
    }

    private void setType(RoomType type)
    {
        this.type = type;
    }

    private RackRate getRackRate()
    {
        return (RackRate) rackRate();
    }

    private void setRackRate(RackRate rackRate)
    {
        final RackRate rate = getRackRate();
        if (rate != null)
        {
            rates.remove(rate);
        }
        rates.add(rackRate);
    }

    public Set<Rate> getRates()
    {
        return rates;
    }

    public void addRate(Rate rate)
    {
        rates.add(rate);
    }

    private void setRates(Set<Rate> rates)
    {
        this.rates = rates;
    }

    private HousekeepingStatus getHousekeepingStatus()
    {
        return housekeepingStatus;
    }

    private void setHousekeepingStatus(HousekeepingStatus housekeepingStatus)
    {
        this.housekeepingStatus = housekeepingStatus;
    }

    private RoomAvailability getAvailability()
    {
        return availability;
    }

    public void setAvailability(RoomAvailability availability)
    {
        this.availability = availability;
    }

    private Integer getMaxExtraBeds()
    {
        return maxExtraBeds;
    }

    private void setMaxExtraBeds(Integer maxExtraBeds)
    {
        this.maxExtraBeds = maxExtraBeds;
    }

    private Occupancy getOccupancy()
    {
        return occupancy;
    }

    private void setOccupancy(Occupancy occupancy)
    {
        this.occupancy = occupancy;
    }

    private String getPrefix()
    {
        return prefix;
    }

    private void setPrefix(String prefix)
    {
        this.prefix = prefix;
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
        Room rhs = (Room) obj;
        return new EqualsBuilder()
                .append(prefix, rhs.prefix)
                .append(name, rhs.name)
                .append(type, rhs.type)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(13, 47)
                .append(prefix)
                .append(name)
                .append(type)
                .toHashCode();
    }

    Room()
    {
    }
}
