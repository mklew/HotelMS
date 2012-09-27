package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:39 AM
 *
 *  Room name consists of some kind of prefix, delimeter (or not - can be empty ) and actual name where
 *  name can be room number or some other sort of business identification
 */
public class RoomName
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
}
