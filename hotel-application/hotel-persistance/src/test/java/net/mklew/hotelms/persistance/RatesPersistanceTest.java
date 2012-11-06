package net.mklew.hotelms.persistance;


import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Database end test for Rates: SeasonRate and PackageRate
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/6/12
 *        Time: 11:29 PM
 */
public class RatesPersistanceTest
{
    private SessionFactory sessionFactory;

    @BeforeMethod
    private void before() throws Exception
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void should_save_rates_and_retrieve_them_with_success()
    {
        Money standardPrice = Money.parse("USD 85");
        Money upchargeExtraPerson = Money.parse("USD 80");
        Money upchargeExtraBed = Money.parse("USD 75");
        Room room = getMeRoom();

        AvailabilityPeriod avibilityPeriod = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(5), true);
        Season season = new BasicSeason("season name", avibilityPeriod);
        Rate seasonRate = new SeasonRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, room, season);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(room);

        session.save(season);

        session.save(seasonRate);

        session.getTransaction().commit();
        session.close();
    }

    private Room getMeRoom()
    {
        final RoomName roomName = new RoomName("101");
        final RoomType roomType = new RoomType("cheap");
        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        final RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        return new Room("C", roomName, roomType, rackRate, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, maxExtraBeds, occupancy);
    }

}
