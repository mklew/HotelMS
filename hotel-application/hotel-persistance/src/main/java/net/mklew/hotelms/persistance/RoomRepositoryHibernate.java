package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/11/12
 *        Time: 5:55 PM
 */
public class RoomRepositoryHibernate extends HibernateRepository implements RoomRepository
{

    public RoomRepositoryHibernate(HibernateSessionFactory hibernateSessionFactory)
    {
        super(hibernateSessionFactory);
    }

    @Override
    public Room getRoomByName(RoomName name)
    {
        Session session = getCurrentSession();
        Room retrievedRoom = (Room)session.byId(Room.class).load(name);
        // TODO if retrievedRoom is null then throw some meaningful exception?
        return retrievedRoom;
    }
}
