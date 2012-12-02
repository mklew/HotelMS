package net.mklew.hotelms.domain.guests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 53)
                .append(person)
                .append(country)
                .append(seq)
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
        AddressId rhs = (AddressId) obj;
        return new EqualsBuilder()
                .append(person, rhs.person)
                .append(country, rhs.country)
                .append(seq, rhs.seq)
                .isEquals();
    }
}
