package net.mklew.hotelms.persistance.hibernate.configuration;

import org.hibernate.SessionFactory;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/10/12
 *        Time: 10:59 PM
 */
public class StubHibernateSessionFactory implements HibernateSessionFactory
{
    private final SessionFactory sessionFactory;

    public StubHibernateSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Override
    public void start()
    {
    }

    @Override
    public void stop()
    {
    }
}
