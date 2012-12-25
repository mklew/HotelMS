package net.mklew.hotelms.domain.room;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:39 AM
 *        <p/>
 *        Room name consists of some kind of prefix, delimeter (or not - can be empty ) and actual name where
 *        name can be room number or some other sort of business identification
 */
public class RoomName implements Serializable
{
    public final static String DELIMETER = "#";

    private String name;

    public RoomName(String name)
    {
        this.name = name;
    }

    public String getRoomName()
    {
        return DELIMETER + name;
    }

    public String getRoomNumber()
    {
        return name;
    }

    // hibernate
    private String getName()
    {
        return name;
    }

    // hibernate
    private void setName(String name)
    {
        this.name = name;
    }

    RoomName()
    {
        // hibernate
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
        RoomName rhs = (RoomName) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).
                append(name).
                toHashCode();
    }

    public static RoomName getNameWithoutPrefix(String roomName)
    {
        String name = roomName.replaceFirst(".*" + DELIMETER, "");
        return new RoomName(name);
    }
}
