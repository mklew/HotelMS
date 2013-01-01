package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.guests.Guest;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/2/12
 *        time 5:06 PM
 */
public class ReservationTest
{
    private Reservation reservation;

    @Mock
    private Id id;

    @Mock
    private Guest reservationOwner;

    @Mock
    private Rate rate;

    @Mock
    private ReservationStatus reservationStatus;
    private static final int NUMBER_OF_ADULTS = 2;
    private static final int NUMBER_OF_CHILDREN = 0;
    private static final int EXTRA_BEDS = 0;

    @BeforeMethod
    public void setupMocks()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test(dataProvider = "getEarlierCheckins")
    public void should_move_checkin_earlier(DateTime checkIn, DateTime earlierCheckIn)
    {
        // given
        assertThat(earlierCheckIn.isBefore(checkIn)); // just check that test data is ok
        final DateTime checkOut = new DateTime(new DateMidnight(2012, 12, 10));
        reservation = new Reservation(id, reservationOwner, rate, checkIn, checkOut, NUMBER_OF_ADULTS,
                NUMBER_OF_CHILDREN, EXTRA_BEDS, reservationStatus);

        // when
        reservation.changeCheckInTo(earlierCheckIn);
        final DateTime newCheckIn = reservation.getCheckIn();
        final DateTime checkOutAfterModifications = reservation.getCheckOut();

        // then
        assertThat(newCheckIn).isEqualTo(earlierCheckIn);
        assertThat(checkOutAfterModifications).isEqualTo(checkOut);
    }

    @DataProvider
    private Object[][] getEarlierCheckins()
    {
        return new Object[][]{
                {new DateTime(new DateMidnight(2012, 12, 5)), new DateTime(new DateMidnight(2012, 12, 2))},
                {new DateTime(new DateMidnight(2012, 12, 5)), new DateTime(new DateMidnight(2012, 12, 3))},
                {new DateTime(new DateMidnight(2012, 11, 25)), new DateTime(new DateMidnight(2012, 11, 20))}
        };
    }

    @Test(dataProvider = "getLaterCheckins")
    public void should_move_check_in_later(DateTime checkIn, DateTime laterCheckIn)
    {
        // given
        assertThat(laterCheckIn.isAfter(checkIn)); // just check that test data is ok
        final DateTime checkOut = new DateTime(new DateMidnight(2012, 12, 10));
        reservation = new Reservation(id, reservationOwner, rate, checkIn, checkOut, NUMBER_OF_ADULTS,
                NUMBER_OF_CHILDREN, EXTRA_BEDS, reservationStatus);

        // when
        reservation.changeCheckInTo(laterCheckIn);
        final DateTime newCheckIn = reservation.getCheckIn();
        final DateTime checkOutAfterModifications = reservation.getCheckOut();

        // then
        assertThat(newCheckIn).isEqualTo(laterCheckIn);
        assertThat(checkOutAfterModifications).isEqualTo(checkOut);
    }

    @DataProvider
    public Object[][] getLaterCheckins()
    {
        return new Object[][]{
                {new DateTime(new DateMidnight(2012, 12, 5)), new DateTime(new DateMidnight(2012, 12, 8))},
                {new DateTime(new DateMidnight(2012, 12, 5)), new DateTime(new DateMidnight(2012, 12, 6))},
                {new DateTime(new DateMidnight(2012, 12, 5)), new DateTime(new DateMidnight(2012, 12, 10))},
                {new DateTime(new DateMidnight(2012, 11, 25)), new DateTime(new DateMidnight(2012, 12, 1))}
        };
    }


//    @Test
//    public void should_fail_when_checkin_is_moved_after_checkout()
//    {
//
//    }
//
//    @Test
//    public void should_move_checkout_earlier()
//    {
//
//    }
//
//    @Test
//    public void should_move_checkout_later()
//    {
//
//    }
//
//
//    @Test
//    public void should_fail_when_checkout_is_moved_before_checkin()
//    {
//
//    }
//
//    @Test
//    public void should_move_both_checkin_and_checkout()
//    {
//
//    }

//
//
//    @Test(dataProvider= "getStaysAndLengthOfStays")
//    public void should_calculate_correct_length_of_stay(Interval stay, int expectedLengthOfStay)
//    {
//        // given
//
//        // when
//        reservation = new Reservation(stay);
//        final int lengthOfStay = reservation.lengthOfStay();
//
//        // then
//        assertThat(lengthOfStay).isEqualTo(expectedLengthOfStay);
//    }
//
//    @Test
//    public void should_be_checkin()
//    {
//        // given
//        final boolean EXPECTED_CHECKIN = true;
//        final DateTime start = new DateTime(2012, 01, 21, 16, 31);
//        Interval stay = new Interval(start, new DateTime(2012, 01, 26, 10, 22));
//
//        // when
//        reservation = new Reservation();
//        reservation.set
//        boolean checkIn = reservation.isCheckIn(start.toLocalDate());
//
//        // then
//        assertThat(checkIn).isEqualTo(EXPECTED_CHECKIN);
//    }
//
//    @DataProvider
//    private Object[][] getStaysAndLengthOfStays(){
//        return $$(
//                $(new Interval(new DateTime(2012, 01, 21, 16, 31), new DateTime(2012, 01, 26, 10 , 22)),5),
//                $(new Interval(new DateTime(2012, 01, 21, 23, 55), new DateTime(2012, 01, 26, 00 , 01)),5),
//                $(new Interval(new DateTime(2012, 01, 01, 16, 31), new DateTime(2012, 01, 26, 10 , 22)), 25)
//        );
//    }

}
