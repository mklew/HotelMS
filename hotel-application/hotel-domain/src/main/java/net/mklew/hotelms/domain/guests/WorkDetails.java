package net.mklew.hotelms.domain.guests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:39 PM
 */
class WorkDetails
{
    private String organizationName;
    private String designation;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;

    public WorkDetails()
    {
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public String getDesignation()
    {
        return designation;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public String getPrimaryPhoneNumber()
    {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber)
    {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber()
    {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber)
    {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }


    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 53)
                .append(organizationName)
                .append(designation)
                .append(primaryPhoneNumber)
                .append(secondaryPhoneNumber)
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
        WorkDetails rhs = (WorkDetails) obj;
        return new EqualsBuilder()
                .append(organizationName, rhs.organizationName)
                .append(designation, rhs.designation)
                .append(primaryPhoneNumber, rhs.primaryPhoneNumber)
                .append(secondaryPhoneNumber, rhs.secondaryPhoneNumber)
                .isEquals();
    }
}
