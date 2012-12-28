package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.room.*;
import net.mklew.hotelms.persistance.hibernate.configuration.StubHibernateSessionFactory;
import org.hibernate.Session;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/10/12
 *        Time: 10:51 PM
 */
@Test(groups = "integration")
public class RateRepositoryHibernateTest extends IntegrationTest
{
    @Test(groups = {"integration"})
    public void should_find_all_rates_for_given_room()
    {
        // given
        final int EXPECTED_RATES = 3; // 2 season rates and 1 rack rate
        final RoomName roomName = new RoomName("11001132");
        final RoomType roomType = new RoomType("prettyCheap");
        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        Room room = new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE,
                maxExtraBeds, occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(roomType);
        session.save(room);
        session.getTransaction().commit();
        session.close();

        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(5),
                true);
        Season season = new BasicSeason("season name", availabilityPeriod);
        Rate seasonRate = new SeasonRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, room, season);

        AvailabilityPeriod availabilityPeriod2 = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(3),
                true);
        Season season2 = new BasicSeason("season name 2", availabilityPeriod2);
        Rate seasonRate2 = new SeasonRate(standardPrice.plus(50), upchargeExtraPerson.plus(10),
                upchargeExtraBed.plus(5), room, season2);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(season);
        session.save(season2);
        room.addRate(seasonRate);
        room.addRate(seasonRate2);
        session.saveOrUpdate(room);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        RateRepositoryHibernate rateRepositoryHibernate = new RateRepositoryHibernate(new StubHibernateSessionFactory
                (sessionFactory));
        // when
        final Collection<Rate> allRatesForRoom = rateRepositoryHibernate.getAllRatesForRoom(room);
        session.getTransaction().commit();

        // then
        assertThat(allRatesForRoom.size()).isEqualTo(EXPECTED_RATES);
        assertThat(allRatesForRoom).contains(seasonRate, seasonRate2);
    }
}
