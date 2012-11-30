package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.booking.Guest;
import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.jcontainer.dna.Configuration;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 7:53 PM
 */
public class GuestRepositoryHibernate extends HibernateRepository implements GuestRepository
{
    private static final int GUEST_LOOK_UP_BY_NAME_LIMIT_DEFAULT = 5;

    private final int guestLookupByNameLimit;

    public GuestRepositoryHibernate(HibernateSessionFactory hibernateSessionFactory, Configuration config)
    {
        super(hibernateSessionFactory);
        guestLookupByNameLimit = config.getChild("guestLookUpByNameLimit").getValueAsInteger(GUEST_LOOK_UP_BY_NAME_LIMIT_DEFAULT);
    }

    @Override
    public Collection<Guest> findAllWhereCommonNameLike(String commonName)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
