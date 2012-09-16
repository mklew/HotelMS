package net.mklew.hotelms.domain.booking.reservation;

import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 7:00 PM
 */
public class IdGeneratorTest
{
    @Test
    public void should_generate_at_least_8_digit_ids()
    {
        DateTime dateTime = new DateTime(2012, 03, 20, 15, 55);
        IdGenerator idGenerator = new IdGenerator(dateTime);

        for(int i = 0 ; i < 99_000_000 ; i++)
        {
            Long actual = idGenerator.generateId(i);
            assertThat(actual).isGreaterThanOrEqualTo(10_000_000);
        }
    }

}
