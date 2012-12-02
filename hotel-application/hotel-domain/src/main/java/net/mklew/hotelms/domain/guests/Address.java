package net.mklew.hotelms.domain.guests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:41 PM
 */
class Address
{
    private AddressId id;
    private String regionName;
    private String areaCode;
    private String city;
    private String street;
    private String streetNumber;
    private String apartmentNumber;
    private AddressType addressType;

    public AddressId getId()
    {
        return id;
    }

    public void setId(AddressId id)
    {
        this.id = id;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber()
    {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber)
    {
        this.apartmentNumber = apartmentNumber;
    }

    public AddressType getAddressType()
    {
        return addressType;
    }

    public void setAddressType(AddressType addressType)
    {
        this.addressType = addressType;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 53)
                .append(id)
                .append(regionName)
                .append(areaCode)
                .append(city)
                .append(street)
                .append(streetNumber)
                .append(apartmentNumber)
                .append(addressType)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Address rhs = (Address) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(regionName, rhs.regionName)
                .append(areaCode, rhs.areaCode)
                .append(city, rhs.city)
                .append(street, rhs.city)
                .append(streetNumber, rhs.streetNumber)
                .append(apartmentNumber, rhs.apartmentNumber)
                .append(addressType, rhs.addressType)
                .isEquals();
    }
}
