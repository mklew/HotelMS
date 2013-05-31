package net.mklew.hotelms.persistance.valve;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

/**
 * Simple valve which closes thread local session - context of current session is current thread
 *
 * @author Marek Lewandowski
 * @since 5/31/13
 */
public class CloseHibernateThreadLocalSession implements Valve
{
    private final HibernateSessionFactory hibernateSessionFactory;

    public CloseHibernateThreadLocalSession(HibernateSessionFactory hibernateSessionFactory)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @Override
    public void process(Context context) throws ProcessingException
    {
        final SessionFactory sessionFactory = hibernateSessionFactory.getSessionFactory();
        final Session currentSession = sessionFactory.getCurrentSession();
        if(currentSession.isOpen())
            currentSession.close();

        ThreadLocalSessionContext.unbind(sessionFactory);
    }
}
