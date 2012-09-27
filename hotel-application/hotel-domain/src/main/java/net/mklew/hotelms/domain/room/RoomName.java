package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 11:39 AM
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
