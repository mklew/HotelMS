package net.mklew.hotelms.domain.room;

import java.io.Serializable;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:39 AM
 *
 *  Room name consists of some kind of prefix, delimeter (or not - can be empty ) and actual name where
 *  name can be room number or some other sort of business identification
 */
public class RoomName implements Serializable
{
    private final static String DELIMETER = "#";

    private String name;

    public RoomName(String name)
    {
        this.name = name;
    }

    public String getRoomName()
    {
        return DELIMETER + name;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomName roomName = (RoomName) o;

        if (name != null ? !name.equals(roomName.name) : roomName.name != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }
}
