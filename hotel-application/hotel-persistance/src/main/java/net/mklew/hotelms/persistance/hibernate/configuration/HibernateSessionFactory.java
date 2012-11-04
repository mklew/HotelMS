package net.mklew.hotelms.persistance.hibernate.configuration;

import org.hibernate.SessionFactory;
import org.picocontainer.Startable;

/**
 * Provides SessionFactory as soon as container's context is up.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 12:10 PM
 */
public interface HibernateSessionFactory extends Startable
{
    SessionFactory getSessionFactory();
}
