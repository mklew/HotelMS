package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.booking.reservation.rates.RateRepository;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 9:14 PM
 */
public class RateRepositoryHibernate  extends HibernateRepository implements RateRepository
{
    private final static String ALL_RATES_FOR_ROOM = "select rate from Rate rate where rate.room = :room";

    public RateRepositoryHibernate(HibernateSessionFactory hibernateSessionFactory)
    {
        super(hibernateSessionFactory);
    }

    @Override
    public Collection<Rate> getAllRatesForRoom(Room room)
    {
        final Session session = getCurrentSession();
        final Query query = session.createQuery(ALL_RATES_FOR_ROOM).setParameter("room", room);
        Collection<Rate> rates = query.list();
        if(rates == null)
        {
            rates = new ArrayList<>();
        }
        rates.add(room.rackRate()); // rack rates are not mapped as rate entity so rack rate need to be added separately
        return rates;
    }
}
