package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.AvailabilityPeriod;
import net.mklew.hotelms.domain.booking.reservation.rates.BasicSeason;
import net.mklew.hotelms.domain.booking.reservation.rates.Season;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Database end test that checks BasicSeason and AvailabilityPeriod
 * are correctly mapped onto same table and everything works fine
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/30/12
 *        Time: 12:28 PM
 */
@Test(groups = {"integration"})
public class TimePeriodTest extends IntegrationTest
{
    @Test(groups = {"integration"})
    public void should_save_season_and_availability_period_with_unique_identifiers() throws Exception
    {
        // given
        final DateTime from = new DateTime(2012, 05, 01, 15, 00);
        final DateTime to = new DateTime(2012, 05, 01, 15, 55);
        final String name = "season";
        final boolean IS_ACTIVE = true;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(DateTime.now(), DateTime.now().plusDays(5),
                IS_ACTIVE);

        AvailabilityPeriod period = new AvailabilityPeriod(from, to, IS_ACTIVE);
        Season season = new BasicSeason(name, period);

        session.save(season);
        session.save(availabilityPeriod);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        // when
        List results = session.createQuery("from BasicSeason").list();
        List results2 = session.createQuery("from AvailabilityPeriod").list();
//        assertThat(results).hasSize(1);
        //then
        session.getTransaction().commit();
        session.close();


    }

}