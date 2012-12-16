package net.mklew.hotelms.domain.guests;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:49 PM
 */
public class GuestBuilder
{

    private GuestBuilder()
    {
    }

    public static GuestBuilder createGuest()
    {
        return new GuestBuilder();
    }

    //TODO implement builder
}
