package net.mklew.hotelms.domain.booking.reservation;

import org.joda.time.DateTime;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 7:00 PM
 */
public class DateBasedIdGeneratorTest
{
    @Test
    public void should_generate_at_least_8_digit_ids()
    {
        DateTime dateTime = new DateTime(2012, 3, 20, 15, 55);
        IdGenerator dateBasedIdGenerator = new DateBasedIdGenerator(dateTime);

        for(int i = 0 ; i < 99 ; i++)
        {
            Long actual = dateBasedIdGenerator.generateId(i);
            assertThat(actual).isGreaterThanOrEqualTo(10_000_000);
        }
    }

}
