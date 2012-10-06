package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.TimePeriod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/30/12
 *        Time: 12:28 PM
 */
public class TimePeriodTest
{
    private SessionFactory sessionFactory;

    @BeforeMethod
    private void before() throws Exception
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void should_retrive_saved_timeperiod() throws Exception
    {
        // given
        final DateTime from = new DateTime(2012, 05, 01, 15, 00);
        final DateTime to = new DateTime(2012, 05, 01, 15, 55);
        final String name = "season";
        final boolean IS_ACTIVE = true;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TimePeriod timePeriod = new TimePeriod(name, from, to, IS_ACTIVE);
        session.save(timePeriod);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        // when
        List results = session.createQuery("from TimePeriod").list();
        assertThat(results).hasSize(1);
        TimePeriod result = (TimePeriod) results.get(0);
        //then
        assertThat(result.getFrom()).isEqualTo(from);
        assertThat(result.getTo()).isEqualTo(to);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getIsActive()).isEqualTo(IS_ACTIVE);

        session.getTransaction().commit();
        session.close();
    }

}