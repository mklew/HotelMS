package net.mklew.hotelms.persistance;


import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.guests.Person;
import net.mklew.hotelms.domain.room.*;
import net.mklew.hotelms.persistance.hibernate.configuration.StubHibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;



/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 6:44 PM
 */
public class GuestRepositoryHibernateTest
{

    private SessionFactory sessionFactory;

    @BeforeMethod
    private void before() throws Exception
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();


    }

    @AfterMethod
    private void after() throws Exception
    {
        sessionFactory.close();
    }

    @Test
    public void should_help_to_find_guests_by_name_and_surname()
    {
        // before
        Session session = sessionFactory.openSession();
        Guest guest1 = new Guest("Mr", "Johnny", "Doe", Gender.MALE, DocumentType.DRIVER_LICENSE, "123-321", "555123456");
        Guest guest2 = new Guest("Mr", "Johnny", "Dowocki", Gender.MALE, DocumentType.DRIVER_LICENSE, "111-111", "555111111");
        Guest guest3 = new Guest("Ms", "Joanna", "Basiak", Gender.FEMALE, DocumentType.PASSPORT_ID, "132312", "1214124");

        session.beginTransaction();

        session.save(guest1);
        session.save(guest2);
        session.save(guest3);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        GuestRepository guestRepository = new GuestRepositoryHibernate(new StubHibernateSessionFactory(sessionFactory));

        // when
        final Collection<Guest> guests = guestRepository.findAllWhereNameLike("Jo", "Do");

        session.getTransaction().commit();
        // then
        assertThat(guests).hasSize(2).contains(guest1, guest2);

    }
}
