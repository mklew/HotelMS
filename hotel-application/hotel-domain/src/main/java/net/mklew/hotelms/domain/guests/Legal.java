package net.mklew.hotelms.domain.guests;

import net.mklew.hotelms.domain.money.Discount;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 12:23 PM
 */
public class Legal extends Person
{
    private String businessName;
    private Discount discount;
    private Guest contactPerson;

    public Legal()
    {
    }

    public String getBusinessName()
    {
        return businessName;
    }

    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }

    public Discount getDiscount()
    {
        return discount;
    }

    public void setDiscount(Discount discount)
    {
        this.discount = discount;
    }

    public Guest getContactPerson()
    {
        return contactPerson;
    }

    public void setContactPerson(Guest contactPerson)
    {
        this.contactPerson = contactPerson;
    }
}
