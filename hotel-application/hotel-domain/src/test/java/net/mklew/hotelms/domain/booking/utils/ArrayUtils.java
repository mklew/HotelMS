package net.mklew.hotelms.domain.booking.utils;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/9/12
 * time 10:22 PM
 */
public class ArrayUtils
{
    public static Object[] $(Object ... objects){
        return objects;
    }

    public static Object[][] $$(Object[] ... objects){
        return objects;
    }
}
