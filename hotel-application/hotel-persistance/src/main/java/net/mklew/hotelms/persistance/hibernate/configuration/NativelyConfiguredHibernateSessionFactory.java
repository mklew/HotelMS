package net.mklew.hotelms.persistance.hibernate.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jcontainer.dna.Logger;

/**
 * Creates SessionFactory based on default Hibernate.cfg.xml configuration found in default location.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 12:12 PM
 */
public class NativelyConfiguredHibernateSessionFactory implements HibernateSessionFactory
{
    private final SessionFactory sessionFactory;

    public NativelyConfiguredHibernateSessionFactory(Logger logger)
    {
        logger.debug("Started configuring hibernate");
        Configuration configuration = new Configuration().configure();
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        logger.debug("Hibernate configured successfully");
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Override
    public Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
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
