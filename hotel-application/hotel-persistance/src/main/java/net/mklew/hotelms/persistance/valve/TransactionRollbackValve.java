package net.mklew.hotelms.persistance.valve;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 12/23/12
 * time 11:02 PM
 */
public class TransactionRollbackValve implements Valve
{
    private final Logger logger;
    private final HibernateSessionFactory hibernateSessionFactory;

    public TransactionRollbackValve(final Logger logger, final HibernateSessionFactory hibernateSessionFactory)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.logger = logger;
    }

    @Override
    public void process(Context context) throws ProcessingException
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (transaction != null && !transaction.wasCommitted())
        {
            try
            {
                transaction.rollback();
            } catch (Exception e)
            {
                logger.error("Cannot rollback transaction", e);
            }
        }
    }
}
