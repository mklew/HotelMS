package net.mklew.hotelms.persistance.hibernate.configuration;

import net.mklew.hotelms.domain.booking.reservation.rates.RackRate;
import net.mklew.hotelms.domain.room.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;
import org.joda.money.Money;
import org.picocontainer.Startable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Populates database with some data upon startup.
 * One place to hardcore everything
 *
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
        final Pair<Collection<RoomType>, Collection<Room>> roomTypesAndRooms = bootstrapRoomTypes();
        Collection<RoomType> types = roomTypesAndRooms.getKey();
        Collection<Room> rooms = roomTypesAndRooms.getValue();

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

        session.getTransaction().commit();
        session.close();
        logger.debug("Finished bootstrapping database");
    }

    private Pair<Collection<RoomType>, Collection<Room>> bootstrapRoomTypes()
    {
        Collection<RoomType> types = new ArrayList<>();
        RoomType luxury = new RoomType("luxury");
        RoomType cheap = new RoomType("cheap");
        RoomType niceOne = new RoomType("nice one");

        types.addAll(Arrays.asList(luxury, cheap, niceOne));
        Collection<Room> rooms = new ArrayList<>();

        Money standardPrice = Money.parse("USD 100");
        Money upchargeExtraPerson = Money.parse("USD 110");
        Money upchargeExtraBed = Money.parse("USD 120");
        RackRate rackRate = new RackRate(standardPrice, upchargeExtraPerson, upchargeExtraBed, null);
        RackRate rackRate1 = new RackRate(standardPrice.plus(10), upchargeExtraPerson.plus(10), upchargeExtraBed.plus(10), null);
        RackRate rackRate2 = new RackRate(standardPrice.plus(20), upchargeExtraPerson.plus(20), upchargeExtraBed.plus(20), null);
        RackRate rackRate3 = new RackRate(standardPrice.plus(30), upchargeExtraPerson.plus(30), upchargeExtraBed.plus(30), null);
        RackRate rackRate4 = new RackRate(standardPrice.plus(40), upchargeExtraPerson.plus(40), upchargeExtraBed.plus(40), null);
        RackRate rackRate5 = new RackRate(standardPrice.plus(50), upchargeExtraPerson.plus(50), upchargeExtraBed.plus(50), null);

        Room L100 = new Room("L", new RoomName("100"), luxury, rackRate, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 1, new Occupancy(3, 4));
        Room L101 = new Room("L", new RoomName("101"), luxury, rackRate1, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 1, new Occupancy(2, 3));
        Room L102 = new Room("L", new RoomName("102"), luxury, rackRate2, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 3, new Occupancy(2,4));
        Room C103 = new Room("C", new RoomName("103"), cheap, rackRate3, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 4, new Occupancy(4, 10));
        Room C104 = new Room("C", new RoomName("104"), cheap, rackRate4, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 5, new Occupancy(6, 12));
        Room N105 = new Room("N", new RoomName("105"), niceOne, rackRate5, HousekeepingStatus.CLEAN, RoomAvailability.AVAILABLE, 2, new Occupancy(2, 5));

        rooms = Arrays.asList(L100, L101, L102, C103, C104, N105);

        return new ImmutablePair<Collection<RoomType>, Collection<Room>>(types, rooms);
    }

    @Override
    public void stop()
    {
    }
}
