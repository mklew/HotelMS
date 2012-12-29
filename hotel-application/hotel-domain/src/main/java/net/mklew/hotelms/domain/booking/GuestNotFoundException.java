package net.mklew.hotelms.domain.booking;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/29/12
 *        time 6:03 PM
 */
public class GuestNotFoundException extends Exception
{
    private final long id;

    public GuestNotFoundException(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }
}
