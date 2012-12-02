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
}
