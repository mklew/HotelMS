package net.mklew.hotelms.persistance;


import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Database end test for Rates: SeasonRate and PackageRate
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/6/12
 *        Time: 11:29 PM
 */
@Test(groups = {"integration"})
public class RatesPersistanceTest extends IntegrationTest
{
    @Test(groups = {"integration"})
    public void should_save_rates_and_retrieve_them_with_success()
    {
        Money standardPrice = Money.parse("USD 85");
        Money upchargeExtraPerson = Money.parse("USD 80");
        Money upchargeExtraBed = Money.parse("USD 75");
        RoomType roomType = getMeRoomType();
        Room room = getMeRoom(roomType);

        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(5),
                true);
        Season season = new BasicSeason("season name", availabilityPeriod);
        Rate seasonRate = new SeasonRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, room, season);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(roomType);
//        session.save(room);

        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();

        //        session.save(roomType);
        session.save(room);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(season);

        session.save(seasonRate);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        final List<Rate> list = session.createQuery("from Rate").list();
        assertThat(list).contains(seasonRate);
        session.getTransaction().commit();
        session.close();

    }

    @Test(groups = {"integration"}, expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
    public void season_rate_should_violate_db_constraints_when_saved_without_season()
    {
        Money standardPrice = Money.parse("USD 85");
        Money upchargeExtraPerson = Money.parse("USD 80");
        Money upchargeExtraBed = Money.parse("USD 75");
        RoomType roomType = getMeRoomType();
        final RoomName roomName = new RoomName("103");

        final Money roomStandardPrice = Money.parse("USD 100");
        final Money roomUpchargeExtraPerson = Money.parse("USD 50");
        final Money roomUpchargeExtraBed = Money.parse("USD 20");
        final RackRate rackRate = new RackRate(roomStandardPrice, roomUpchargeExtraPerson, roomUpchargeExtraBed, null);
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        Room room = new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE,
                maxExtraBeds, occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);


        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(5),
                true);
        Season season = new BasicSeason("season name", availabilityPeriod);
        Rate seasonRate = new SeasonRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, room, null);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(room);

        session.save(seasonRate);

        session.getTransaction().commit();
        session.close();
    }

//    @Test
//    public void package_rate_should_violate_db_constraints_when_saved_without_package()
//    {
//
//    }

    private RoomType getMeRoomType()
    {
        final RoomType roomType = new RoomType("cheap" + DateTime.now().toString());
        return roomType;
    }

    private Room getMeRoom(RoomType roomType)
    {
        final RoomName roomName = new RoomName("101001" + DateTime.now().toString());

        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        final RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        return new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, maxExtraBeds,
                occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);
    }

}
