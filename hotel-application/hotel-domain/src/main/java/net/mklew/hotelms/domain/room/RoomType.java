package net.mklew.hotelms.domain.room;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:37 AM
 *
 *  Type of the room. It can be for example Cheap room or Luxury room
 *  or Presidential or some other kind of defined type
 */
public class RoomType
{
    private String typeName;

    public RoomType(String typeName)
    {
        this.typeName = typeName;
    }

    public RoomType changeName(String name)
    {
        return new RoomType(name);
    }

    public String getTypeName()
    {
        return typeName;
    }

    // hibernate
    private void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        RoomType rhs = (RoomType) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(typeName, rhs.typeName)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).
                append(typeName).
                toHashCode();
    }


    RoomType()
    {
        // hibernate
    }
}
