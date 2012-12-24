package net.mklew.hotelms.inhouse.web.dto;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 5:21 PM
 */
public class MissingGuestInformation extends Exception
{
    public MissingGuestInformation(String message)
    {
        super(message);
    }
}
