package net.mklew.hotelms.persistance;

import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jcontainer.dna.Logger;
import org.testng.annotations.*;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.mock;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 8:11 PM
 */
@Test
public class IntegrationTest
{
    protected SessionFactory sessionFactory;
    protected HibernateSessionFactory hibernateSessionFactory;
    private final static AtomicInteger seq = new AtomicInteger();

    @BeforeMethod
    public void setUpHibernate() throws Exception
    {
        Configuration configuration = new Configuration().configure();
        final String connection = "jdbc:h2:mem:db" + seq.incrementAndGet() + ";DB_CLOSE_DELAY=0;MVCC=true";
        configuration.setProperty("hibernate.connection.url", connection);
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        final StubHibernateSessionFactory hibernateSessionFactory = new StubHibernateSessionFactory(sessionFactory);
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.sessionFactory = hibernateSessionFactory.getSessionFactory();
    }

    @AfterMethod
    public void putItDown() throws Exception
    {
        sessionFactory.close();
    }
}
