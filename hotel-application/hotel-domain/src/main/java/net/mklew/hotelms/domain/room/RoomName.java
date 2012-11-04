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

    private String prefix;
    private String name;

    public RoomName(String name, String prefix)
    {
        this.name = name;
        this.prefix = prefix;
    }

    public String getRoomName()
    {
        return prefix + DELIMETER + name;
    }


    // hibernate
    private String getPrefix()
    {
        return prefix;
    }

    // hibernate
    private void setPrefix(String prefix)
    {
        this.prefix = prefix;
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
}
