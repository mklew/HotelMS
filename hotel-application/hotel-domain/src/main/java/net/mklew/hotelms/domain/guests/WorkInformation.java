package net.mklew.hotelms.domain.guests;

import org.apache.commons.lang3.Validate;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 5:36 PM
 */
public final class WorkInformation
{
    private final WorkDetails workDetails;
    private final Address address;

    public WorkInformation(WorkDetails workDetails, Address address)
    {
        Validate.notNull(workDetails);
        Validate.notNull(address);
        Validate.isTrue(address.getAddressType().equals(AddressType.WORK_ADDRESS), "Work information must have work " +
                "address but had " + address.getAddressType()
                .name(), address);

        this.workDetails = workDetails;
        this.address = address;
    }

    public WorkDetails getWorkDetails()
    {
        return workDetails;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getOrganizationName()
    {
        return workDetails.getOrganizationName();
    }

    public String getSecondaryPhoneNumber()
    {
        return workDetails.getSecondaryPhoneNumber();
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber)
    {
        workDetails.setPrimaryPhoneNumber(primaryPhoneNumber);
    }

    public void setDesignation(String designation)
    {
        workDetails.setDesignation(designation);
    }

    public String getDesignation()
    {
        return workDetails.getDesignation();
    }

    public void setOrganizationName(String organizationName)
    {
        workDetails.setOrganizationName(organizationName);
    }

    public String getPrimaryPhoneNumber()
    {
        return workDetails.getPrimaryPhoneNumber();
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber)
    {
        workDetails.setSecondaryPhoneNumber(secondaryPhoneNumber);
    }

    public String getRegionName()
    {
        return address.getRegionName();
    }

    public void setRegionName(String regionName)
    {
        address.setRegionName(regionName);
    }

    public String getAreaCode()
    {
        return address.getAreaCode();
    }

    public void setAreaCode(String areaCode)
    {
        address.setAreaCode(areaCode);
    }

    public String getCity()
    {
        return address.getCity();
    }

    public void setCity(String city)
    {
        address.setCity(city);
    }

    public String getStreet()
    {
        return address.getStreet();
    }

    public void setStreet(String street)
    {
        address.setStreet(street);
    }

    public String getStreetNumber()
    {
        return address.getStreetNumber();
    }

    public void setStreetNumber(String streetNumber)
    {
        address.setStreetNumber(streetNumber);
    }

    public String getApartmentNumber()
    {
        return address.getApartmentNumber();
    }

    public void setApartmentNumber(String apartmentNumber)
    {
        address.setApartmentNumber(apartmentNumber);
    }

    public AddressType getAddressType()
    {
        return address.getAddressType();
    }

    public void setAddressType(AddressType addressType)
    {
        address.setAddressType(addressType);
    }
}
