package net.mklew.hotelms.domain.booking;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Id class gives special meaning. Better than using String
 *
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/9/12
 * time 8:32 PM
 */
public class Id
{
    private final String id;

    public Id(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public Id concat(Id id)
    {
        return new Id(this.id + '.' + id.getId());
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
}
