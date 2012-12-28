package net.mklew.hotelms.domain.booking;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Id object, identifies Reservation as well as Group
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/9/12
 *        time 8:32 PM
 */
public class Id implements Serializable
{
    public static final transient Id NO_ID = new NoId();
    private long id;

    public Id(long id)
    {
        this.id = id;
    }

    Id()
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
        Id rhs = (Id) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(13, 47)
                .append(id)
                .toHashCode();
    }

    public String getPrintableId()
    {
        return String.valueOf(id);
    }
}
