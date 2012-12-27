package net.mklew.hotelms.persistance.identity;

import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.reservation.DateBasedIdGenerator;
import net.mklew.hotelms.domain.booking.reservation.IdGenerator;
import net.mklew.hotelms.domain.booking.reservation.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 12:58 PM
 */
public class ReservationSaveListener implements SaveOrUpdateEventListener
{
    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException
    {
        if (event.getObject() != null && event.getObject().getClass().equals(Reservation.class))
        {
            final Reservation reservation = (Reservation) event.getObject();
            if (reservation.getReservationId().equals(Id.NO_ID))
            {
                final EventSource session = event.getSession();

                final long nextId = ReservationSequenceFetcher.getNext(session);
                IdGenerator idGenerator = DateBasedIdGenerator.newGenerator();
                final Long newId = idGenerator.generateId(nextId);
                Id id = new Id(newId);
                reservation.setReservationId(id);
            }
        }
    }
}
