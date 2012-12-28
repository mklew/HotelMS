package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.domain.booking.reservation.ReservationFactory;
import net.mklew.hotelms.domain.booking.reservation.ReservationFactoryImpl;
import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.joda.money.Money;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 4:38 PM
 */
@Test(groups = {"integration"})
public class ReservationRepositoryHibernateTest extends IntegrationTest
{
    @Test(groups = {"integration"})
    public void should_find_reservations_around_dates() throws Exception
    {
        // given prepared data and
        prepareData();
        final DateTime start = new DateTime(new DateMidnight(2012, 10, 4));
        final DateTime end = new DateTime(new DateMidnight(2012, 10, 8));
        final int NUMBER_OF_RESERVATIONS_BETWEEN_DATES = 2;

        // when
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ReservationRepositoryHibernate objectUnderTest = new ReservationRepositoryHibernate(hibernateSessionFactory);
        final Collection<Reservation> reservations = objectUnderTest.findAllReservationsAroundDates
                (start, end);

        // then
        assertThat(reservations).hasSize(NUMBER_OF_RESERVATIONS_BETWEEN_DATES);

        session.getTransaction().commit();
    }

    private void prepareData()
    {
        // given
        final RoomName roomName = new RoomName("106");
        final RoomType roomType = new RoomType("cheap");
        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        Room room = new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE,
                maxExtraBeds, occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);
        Guest guest1 = new Guest("Mr", "Johnny", "Doe", Gender.MALE, DocumentType.DRIVER_LICENSE, "123-321",
                "555123456");

        Season season = new BasicSeason("winter special", new AvailabilityPeriod(DateTime.now(),
                DateTime.now().plusDays(90), true));
        Season season2 = new BasicSeason("christmas special", new AvailabilityPeriod(DateTime.now(),
                DateTime.now().plusDays(30), true));

        Rate seasonRate = new SeasonRate(Money.parse("USD 50"), Money.parse("USD 60"), Money.parse("USD 70"), room,
                season);
        Rate seasonRate1 = new SeasonRate(Money.parse("USD 20"), Money.parse("USD 60"), Money.parse("USD 70"), room,
                season2);

        final DateTime checkIn = new DateTime(new DateMidnight(2012, 10, 5));
        final DateTime checkOut = new DateTime(new DateMidnight(2012, 10, 10));
        final int numberOfAdults = 2;
        final int numberOfChildren = 0;
        final int extraBeds = 0;

        ReservationFactory factory = new ReservationFactoryImpl();
        Reservation reservation = factory.createSingleReservation(guest1, room, seasonRate, checkIn, checkOut,
                numberOfAdults, numberOfChildren, extraBeds);
        Reservation reservation2 = factory.createSingleReservation(guest1, room, room.rackRate(), checkIn, checkOut,
                numberOfAdults, numberOfChildren, extraBeds);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(roomType);
        session.save(room);
        session.save(guest1);
        session.save(season);
        session.save(season2);
        session.save(seasonRate);
        session.save(seasonRate1);

        session.getTransaction().commit();
        session.close();

        // when
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(reservation);
        session.save(reservation2);

        session.getTransaction().commit();
        session.close();
    }
}
