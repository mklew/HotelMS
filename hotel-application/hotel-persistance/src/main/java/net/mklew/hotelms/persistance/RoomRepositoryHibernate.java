package net.mklew.hotelms.persistance;

import net.mklew.hotelms.domain.room.*;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Collection;

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
    public Room getRoomByName(RoomName name) throws RoomNotFoundException
    {
        Session session = getCurrentSession();
        Room retrievedRoom = (Room) session.byId(Room.class).load(name);
        if (retrievedRoom == null)
        {
            throw new RoomNotFoundException("Room named " + name + " has not been found");
        }
        return retrievedRoom;
    }

    @Override
    public Collection<RoomType> getAllRoomTypes()
    {
        Session session = getCurrentSession();
        Collection<RoomType> roomTypes = session.createQuery("from RoomType").list();
        return roomTypes;
    }

    @Override
    public Collection<Room> getAllRooms()
    {
        Session session = getCurrentSession();
        Collection<Room> allRooms = session.createQuery("from Room").list();
        return allRooms;
    }
}
