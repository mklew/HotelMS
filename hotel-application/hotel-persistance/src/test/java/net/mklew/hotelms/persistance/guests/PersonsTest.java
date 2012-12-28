package net.mklew.hotelms.persistance.guests;

import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.guests.Person;
import net.mklew.hotelms.persistance.IntegrationTest;
import org.hibernate.Session;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Just a save & retrieve test
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 5:17 PM
 */
@Test(groups = {"integration"})
public class PersonsTest extends IntegrationTest
{
    @Test(groups = {"integration"})
    public void should_save_and_retrieve_guest_with_basic_information()
    {
        Guest guest = new Guest("Mr", "John", "Doe", Gender.MALE, DocumentType.DRIVER_LICENSE, "123-321", "555123456");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(guest);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        final Collection<Person> persons = session.createQuery("from Person").list();
        assertThat(persons).hasSize(1);

        session.getTransaction().commit();
        session.close();
    }


}
