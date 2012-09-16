package net.mklew.hotelms.domain.booking;

/**
 * Id class gives special meaning. Better than using String
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/9/12
 * time 8:32 PM
 */
public final class Id
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
}
