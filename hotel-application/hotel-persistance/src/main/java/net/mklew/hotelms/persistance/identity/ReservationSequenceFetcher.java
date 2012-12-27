package net.mklew.hotelms.persistance.identity;


import org.hibernate.Session;

import javax.persistence.*;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/27/12
 *        time 12:52 PM
 */
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "reservation_sequence")
public class ReservationSequenceFetcher
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private long id;

    public long getId()
    {
        return id;
    }

    public static long getNext(Session session)
    {
        ReservationSequenceFetcher rsf = new ReservationSequenceFetcher();
        session.save(rsf);
        session.flush();
        return rsf.getId();
    }
}
