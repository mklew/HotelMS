package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.joda.money.Money;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/26/12
 *        time 9:57 PM
 */
@Test
public class ReservationPersistanceTest extends IntegrationTest
{
    @Test
    public void should_save_reservation_and_retrieve_it() throws Exception
    {
        // given
        final RoomName roomName = new RoomName("106666123");
        final RoomType roomType = new RoomType("cheapOne");
        final Money standardPrice = Money.parse("USD 100");
        final Money upchargeExtraPerson = Money.parse("USD 50");
        final Money upchargeExtraBed = Money.parse("USD 20");
        //final RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
        final int maxExtraBeds = 2;
        final Occupancy occupancy = new Occupancy(4, 2);
        Room room = new Room("C", roomName, roomType, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE,
                maxExtraBeds, occupancy, standardPrice, upchargeExtraPerson, upchargeExtraBed);
        Guest guest1 = new Guest("Mr", "Johnny", "Doe", Gender.MALE, DocumentType.DRIVER_LICENSE, "123-321",
                "555123456");

        final DateTime checkIn = new DateTime(new DateMidnight(2012, 10, 5));
        final DateTime checkOut = new DateTime(new DateMidnight(2012, 10, 10));
        final int numberOfAdults = 2;
        final int numberOfChildren = 0;
        final int extraBeds = 0;

        Reservation reservation = new Reservation(Id.NO_ID, guest1,
                room.rackRate(), checkIn, checkOut, numberOfAdults, numberOfChildren, extraBeds,
                ReservationStatus.TECHNICAL);
        Reservation reservation2 = new Reservation(Id.NO_ID, guest1,
                room.rackRate(), checkIn, checkOut, numberOfAdults, numberOfChildren, extraBeds,
                ReservationStatus.TECHNICAL);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(roomType);
        session.save(room);
        session.save(guest1);

        session.getTransaction().commit();
        session.close();

        // when
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(reservation);
        session.save(reservation2);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        final List<Reservation> list = session.createQuery("from Reservation ").list();
        Reservation retrievedReservation = list.get(0);
        Reservation retrievedReservation2 = list.get(1);

        assertThat(retrievedReservation.getNumberOfAdults()).isEqualTo(numberOfAdults);
        assertThat(String.valueOf(retrievedReservation.getReservationId().getId()).length()).isGreaterThanOrEqualTo(8);
        assertThat(String.valueOf(retrievedReservation2.getReservationId().getId()).length()).isGreaterThanOrEqualTo(8);
        assertThat(retrievedReservation.getReservationId()).isNotEqualTo(retrievedReservation2.getReservationId());

    }
}
