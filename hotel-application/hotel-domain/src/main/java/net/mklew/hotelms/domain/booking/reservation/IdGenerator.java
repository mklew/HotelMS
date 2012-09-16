package net.mklew.hotelms.domain.booking.reservation;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 6:59 PM
 */

/**
 * IdGenerator creates business friendly ids based on unique ids from for e.g. database sequence
 * Ids have at least 8 digits and are quite distinct even when generated from sequence.
 */
public class IdGenerator
{
    private final DateTime dateTime;

    public static IdGenerator newGenerator()
    {
        return new IdGenerator(DateTime.now());
    }

    public IdGenerator(DateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    /**
     * Will produce at least 8 digit number. Numbers are unique as long as passed parameter is unique.
     * @param id unique id like database sequence
     * @return Long with at 8 digits
     */
    public Long generateId(long id)
    {
        int part1 = dateTime.getDayOfMonth() + dateTime.getSecondOfMinute();
        if (part1 < 10)
        {
            part1 += 10;
        }
        int part2 = dateTime.getHourOfDay() + 10;
        int part3 = dateTime.getMinuteOfHour() + 10;
        int part4 = dateTime.getSecondOfMinute() + 10;

        part2 += part3 + part4;
        part3 += part4;

        part1 *= multiplier(6);
        part2 *= multiplier(4);
        part3 *= multiplier(2);

        // has 8 digits
        int base = part1 + part2 + part3 + part4;

        return base + id;
    }

    private int multiplier(int zeros)
    {
        return (int)Math.pow(10, zeros);
    }

    public static void main(String [] args)    throws Exception
    {
        for(int i = 0 ; i < 10 ; i++)
        {
            IdGenerator generator = newGenerator();

            System.out.println(generator.generateId(i));
            TimeUnit.SECONDS.sleep(5);
        }
    }


}
