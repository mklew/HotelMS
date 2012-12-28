package net.mklew.hotelms.persistance.valve;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/28/12
 *        time 11:26 PM
 */
public class DelayTillCommittedValve implements Valve
{
    private final Logger logger;
    private final HibernateSessionFactory hibernateSessionFactory;

    public DelayTillCommittedValve(final Logger logger, final HibernateSessionFactory hibernateSessionFactory)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.logger = logger;
    }

    @Override
    public void process(Context context) throws ProcessingException
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        try
        {
            int counter = 0;
            while ((session.getTransaction().isActive()) && counter < 10)
            {
                Thread.sleep(100);
                counter = counter + 1;
            }
            if (session.getTransaction().isActive())
            {
                session.getTransaction().rollback();
            }
        }
        catch (HibernateException e)
        {
            logger.debug("Cannot check if transaction is active", e);
        }
        catch (InterruptedException e)
        {
            logger.debug("interrupted thread", e);
        }
    }
}
