package net.mklew.hotelms.domain.guests;

import java.util.Set;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/1/12
 *        time 5:59 PM
 */
public abstract class Person
{
    protected long id;
    protected Set<Address> addresses;

    public Person()
    {
    }

    public long getId()
    {
        return id;
    }

    void setId(long id)
    {
        this.id = id;
    }

    public Set<Address> getAddresses()
    {
        return addresses;
    }

    void setAddresses(Set<Address> addresses)
    {
        this.addresses = addresses;
    }
}
