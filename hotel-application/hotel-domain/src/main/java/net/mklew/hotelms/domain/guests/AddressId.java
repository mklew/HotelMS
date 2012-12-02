package net.mklew.hotelms.domain.guests;

import java.io.Serializable;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 3:17 PM
 */
public class AddressId implements Serializable
{
    private Person person;
    private Country country;
    private long seq;


    public AddressId()
    {
    }

    public AddressId(Person person, Country country, long seq)
    {
        this.person = person;
        this.country = country;
        this.seq = seq;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public long getSeq()
    {
        return seq;
    }

    public void setSeq(long seq)
    {
        this.seq = seq;
    }
}
