package net.mklew.hotelms.domain.room;

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

    RoomType()
    {
        // hibernate
    }
}
