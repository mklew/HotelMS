package net.mklew.hotelms.domain.guests;

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
}
