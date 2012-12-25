package net.mklew.hotelms.inhouse.web.dto.dates;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 2:16 PM
 */
public class DateParserTest
{
    @Test(dataProvider = "getValidDateStrings")
    public void should_parse_valid_strings(String dateString, DateTime expectedDate)
    {
        // given

        // when
        DateTime dateTime = DateParser.fromString(dateString);

        // then
        assertThat(dateTime).isEqualTo(expectedDate);
    }

    @Test(dependsOnMethods = "should_parse_valid_strings", dataProvider = "getValidDateStrings")
    public void should_create_valid_strings_from_dates(String expectedString, DateTime dateToParse)
    {
        // given

        // when
        final String parsed = DateParser.fromDate(dateToParse);

        // then
        assertThat(parsed).isEqualTo(expectedString);
    }

    @SuppressWarnings("unused")
    private Object[][] getValidDateStrings()
    {
        return new Object[][]
                {
                        {"2012-12-01", new DateMidnight(2012, 12, 1)},
                        {"2012-11-24", new DateMidnight(2012, 11, 24)},
                        {"2012-01-01", new DateMidnight(2012, 1, 1)}
                };
    }


}
