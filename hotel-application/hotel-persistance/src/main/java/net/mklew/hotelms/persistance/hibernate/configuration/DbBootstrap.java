package net.mklew.hotelms.persistance.hibernate.configuration;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.domain.booking.reservation.rates.*;
import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.*;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;
import org.joda.money.Money;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.picocontainer.Startable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Populates database with some data upon startup.
 * One place to hardcore everything
 * <p/>
 * It should not be used for production!
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/11/12
 *        Time: 8:11 PM
 */
public class DbBootstrap implements Startable
{
    final private Logger logger;
    final private HibernateSessionFactory hibernateSessionFactory;

    public DbBootstrap(Logger logger, HibernateSessionFactory hibernateSessionFactory)
    {
        this.logger = logger;
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @Override
    public void start()
    {
        bootstrap();
    }

    private void bootstrap()
    {
        logger.debug("Started bootstrapping database");
        Session session = hibernateSessionFactory.getCurrentSession();

        // bootstrapping data

        Collection<RoomType> types = new ArrayList<>();
        RoomType luxury = new RoomType("luxury");
        RoomType cheap = new RoomType("cheap");
        RoomType niceOne = new RoomType("nice one");

        types.addAll(Arrays.asList(luxury, cheap, niceOne));
        Collection<Room> rooms;

        Money standardPrice = Money.parse("USD 100");
        Money upchargeExtraPerson = Money.parse("USD 110");
        Money upchargeExtraBed = Money.parse("USD 120");
//        RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
//        RackRate rackRate1 = new RackRate(standardPrice.plus(10), upchargeExtraPerson.plus(10),
//                upchargeExtraBed.plus(10), null);
//        RackRate rackRate2 = new RackRate(standardPrice.plus(20), upchargeExtraPerson.plus(20),
//                upchargeExtraBed.plus(20), null);
//        RackRate rackRate3 = new RackRate(standardPrice.plus(30), upchargeExtraPerson.plus(30),
//                upchargeExtraBed.plus(30), null);
//        RackRate rackRate4 = new RackRate(standardPrice.plus(40), upchargeExtraPerson.plus(40),
//                upchargeExtraBed.plus(40), null);
//        RackRate rackRate5 = new RackRate(standardPrice.plus(50), upchargeExtraPerson.plus(50),
//                upchargeExtraBed.plus(50), null);

        Room L100 = new Room("L", new RoomName("100"), luxury, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 1, new Occupancy(3, 4), standardPrice, upchargeExtraPerson,
                upchargeExtraBed);
        Room L101 = new Room("L", new RoomName("101"), luxury, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 1, new Occupancy(2, 3), standardPrice.plus(10),
                upchargeExtraPerson.plus(10),
                upchargeExtraBed.plus(10));
        Room L102 = new Room("L", new RoomName("102"), luxury, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 3, new Occupancy(2, 4), standardPrice.plus(30),
                upchargeExtraPerson.plus(30),
                upchargeExtraBed.plus(30));
        Room C103 = new Room("C", new RoomName("103"), cheap, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 4, new Occupancy(4, 10), standardPrice.plus(30),
                upchargeExtraPerson.plus(30),
                upchargeExtraBed.plus(30));
        Room C104 = new Room("C", new RoomName("104"), cheap, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 5, new Occupancy(6, 12), standardPrice.plus(40),
                upchargeExtraPerson.plus(40),
                upchargeExtraBed.plus(40));
        Room N105 = new Room("N", new RoomName("105"), niceOne, HousekeepingStatus.CLEAN,
                RoomAvailability.AVAILABLE, 2, new Occupancy(2, 5), standardPrice.plus(50),
                upchargeExtraPerson.plus(50),
                upchargeExtraBed.plus(50));

        rooms = Arrays.asList(L100, L101, L102, C103, C104, N105);

        Collection<Rate> rates;

        Season season = new BasicSeason("all time special", new AvailabilityPeriod(DateTime.now(),
                DateTime.now().plusDays(600), true));


        Rate rate1_L100 = new SeasonRate(Money.parse("USD 50"), Money.parse("USD 60"), Money.parse("USD 70"), L100,
                season);

        Rate rate1_L101 = new SeasonRate(Money.parse("USD 60"), Money.parse("USD 70"), Money.parse("USD 60"), L101,
                season);

        Rate rate1_L102 = new SeasonRate(Money.parse("USD 60"), Money.parse("USD 70"), Money.parse("USD 60"), L102,
                season);

        Rate rate1_C103 = new SeasonRate(Money.parse("USD 40"), Money.parse("USD 70"), Money.parse("USD 60"), C103,
                season);

        Rate rate1_C104 = new SeasonRate(Money.parse("USD 70"), Money.parse("USD 70"), Money.parse("USD 60"), C104,
                season);

        Rate rate1_N105 = new SeasonRate(Money.parse("USD 80"), Money.parse("USD 70"), Money.parse("USD 60"), N105,
                season);


        Collection<Season> seasons = Arrays.asList(season);
        rates = Arrays.asList(rate1_L100, rate1_L101, rate1_L102, rate1_C103, rate1_C104, rate1_N105);



        Collection<Guest> guests = Collections.EMPTY_LIST;
        for (Guest guest : guests)
        {
            guest.setNationality("Polish");
        }

        // bootstrapping data
        session.beginTransaction();
        logger.debug("adding room types:");
        for (RoomType type : types)
        {
            session.save(type);
            logger.debug("room type: " + type.toString());
        }
        logger.debug("adding rooms");
        for (Room room : rooms)
        {
            logger.debug("room: " + room.toString());
            session.save(room);
        }

        logger.debug("adding seasons");
        for (Season s : seasons)
        {
            logger.debug("season: " + s.toString());
            session.save(s);
        }

        logger.debug("adding season rates");
        for (Rate rate : rates)
        {
            logger.debug("rate: " + rate.toString());
            session.save(rate);
        }

        logger.debug("adding guests");
        for (Guest guest : guests)
        {
            logger.debug("guest: " + guest.toString());
            session.save(guest);
        }

        logger.debug("adding reservations");

        session.getTransaction().commit();
        logger.debug("Finished bootstrapping database");
    }

    @Override
    public void stop()
    {
    }
}
