package net.mklew.hotelms.persistance;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * Base class for Repositories. Gives each Repository access to current session.
 *
 * Repository is concept taken from Domain Driven Design approach.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 9:26 PM
 */
public abstract class HibernateRepository
{
    private HibernateSessionFactory hibernateSessionFactory;

    public HibernateRepository(HibernateSessionFactory hibernateSessionFactory)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    protected Session getCurrentSession() {
        return hibernateSessionFactory.getSessionFactory().getCurrentSession();
    }
}
