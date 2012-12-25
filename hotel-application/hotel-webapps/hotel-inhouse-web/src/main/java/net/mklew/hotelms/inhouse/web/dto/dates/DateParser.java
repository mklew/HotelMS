package net.mklew.hotelms.inhouse.web.dto.dates;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 2:07 PM
 */
public class DateParser
{
    private static final DateTimeFormatter formatter = ISODateTimeFormat.date(); // yyyy-MM-dd

    public static DateTime fromString(String dateString)
    {
        Validate.notNull(dateString);
        return formatter.parseDateTime(dateString);
    }

    public static String fromDate(DateTime date)
    {
        Validate.notNull(date);
        return formatter.print(date);
    }

    public static String printNow()
    {
        ReadableInstant readableInstant = null;
        return formatter.print(readableInstant);
    }

}
