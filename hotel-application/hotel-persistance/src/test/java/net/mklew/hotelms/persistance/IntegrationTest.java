package net.mklew.hotelms.persistance;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import net.mklew.hotelms.persistance.hibernate.configuration.NativelyConfiguredHibernateSessionFactory;
import org.hibernate.SessionFactory;
import org.jcontainer.dna.Logger;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 8:11 PM
 */
@Test(groups = {"integration"})
public class IntegrationTest
{
    protected SessionFactory sessionFactory;
    protected HibernateSessionFactory hibernateSessionFactory;

    @BeforeGroups(groups = {"integration"})
    public void setUpHibernate() throws Exception
    {
        Logger logger = mock(Logger.class);
        NativelyConfiguredHibernateSessionFactory hibernateSessionFactory = new
                NativelyConfiguredHibernateSessionFactory(logger);
        this.sessionFactory = hibernateSessionFactory.getSessionFactory();
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @AfterGroups(groups = {"integration"})
    public void putItDown() throws Exception
    {
        sessionFactory.close();
    }
}
