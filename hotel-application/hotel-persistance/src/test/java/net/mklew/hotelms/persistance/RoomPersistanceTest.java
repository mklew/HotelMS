package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.RackRate;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.joda.money.Money;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Database end tests for Room
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 10:27 PM
 */
@Test
public class RoomPersistanceTest extends IntegrationTest
{
    @Test
    public void should_successfully_save_and_retrieve_room() throws Exception
    {
        final RoomName roomName = new RoomName("10623");
        final RoomType roomType = new RoomType("cheapCheap");
        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        final RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        Room room = new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE,
                maxExtraBeds, occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);

        //Session session = sessionFactory.getCurrentSession(); // No TransactionManagerLookup specified
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(roomType);
        session.save(room);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Room retrievedRoom = (Room) session.byId(Room.class).load(roomName);

        assertThat(retrievedRoom.rackRate().standardPrice()).isEqualTo(standardPrice);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        final List<Room> list = session.createQuery("from Room r where r.name = :name").setParameter("name",
                roomName).list();
        Room room1 = list.get(0);
        assertThat(room1.rackRate().standardPrice()).isEqualTo(standardPrice);
        assertThat(list).hasSize(1);

    }
}
