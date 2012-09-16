package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.reservation.Reservation;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static net.mklew.hotelms.domain.booking.utils.ArrayUtils.$;
import static net.mklew.hotelms.domain.booking.utils.ArrayUtils.$$;
import static org.fest.assertions.Fail.fail;
import static org.fest.assertions.Assertions.assertThat;


/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/2/12
 * time 5:06 PM
 */
public class ReservationTest
{
    private Reservation reservation;


    @Test(dataProvider= "getStaysAndLengthOfStays")
    public void should_calculate_correct_length_of_stay(Interval stay, int expectedLengthOfStay)
    {
        // given

        // when
        reservation = new Reservation(stay);
        final int lengthOfStay = reservation.lengthOfStay();

        // then
        assertThat(lengthOfStay).isEqualTo(expectedLengthOfStay);
    }

    @Test
    public void should_be_checkin()
    {
        // given
        final boolean EXPECTED_CHECKIN = true;
        final DateTime start = new DateTime(2012, 01, 21, 16, 31);
        Interval stay = new Interval(start, new DateTime(2012, 01, 26, 10, 22));

        // when
        reservation = new Reservation(stay);
        boolean checkIn = reservation.isCheckIn(start.toLocalDate());

        // then
        assertThat(checkIn).isEqualTo(EXPECTED_CHECKIN);
    }

    @DataProvider
    private Object[][] getStaysAndLengthOfStays(){
        return $$(
                $(new Interval(new DateTime(2012, 01, 21, 16, 31), new DateTime(2012, 01, 26, 10 , 22)),5),
                $(new Interval(new DateTime(2012, 01, 21, 23, 55), new DateTime(2012, 01, 26, 00 , 01)),5),
                $(new Interval(new DateTime(2012, 01, 01, 16, 31), new DateTime(2012, 01, 26, 10 , 22)), 25)
        );
    }

}
