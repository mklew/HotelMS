package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.booking.reservation.rates.RateRepository;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 9:14 PM
 */
public class RateRepositoryHibernate extends HibernateRepository implements RateRepository
{
    private final static String ALL_RATES_FOR_ROOM = "select rate from Rate rate where rate.room = :room";

    public RateRepositoryHibernate(HibernateSessionFactory hibernateSessionFactory)
    {
        super(hibernateSessionFactory);
    }

    @Override
    public Collection<Rate> getAllRatesForRoom(Room room)
    {
        return room.getRates();
    }
}
