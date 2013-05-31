package net.mklew.hotelms.persistance.valve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;

/**
 * @author Marek Lewandowski
 * @since 5/31/13
 */
public class OpenSessionAndBindToCurrentThread
    implements Valve
{
    private final HibernateSessionFactory hibernateSessionFactory;

    public OpenSessionAndBindToCurrentThread(HibernateSessionFactory hibernateSessionFactory)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @Override
    public void process(Context context)
        throws ProcessingException
    {
        final SessionFactory sessionFactory = hibernateSessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }
}
