package net.mklew.hotelms.persistance.hibernate.configuration;

import net.mklew.hotelms.persistance.identity.ReservationSaveListener;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 2:30 PM
 */
public class ReservationListenerIntegrator implements Integrator
{
    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry)
    {
        final EventListenerRegistry service = serviceRegistry.getService(EventListenerRegistry.class);
        service.prependListeners(EventType.SAVE, new ReservationSaveListener());
    }

    @Override
    public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry)
    {

    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry)
    {

    }
}
