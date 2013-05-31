package net.mklew.hotelms.inhouse.web.rest;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.domain.room.RoomType;
import net.mklew.hotelms.inhouse.web.dto.RoomDto;
import net.mklew.hotelms.inhouse.web.dto.RoomStats;
import net.mklew.hotelms.inhouse.web.dto.RoomTypeDto;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/25/12
 *        time 7:14 PM
 */
@Path("/room")
public class RoomResource
{
    private final RoomRepository roomRepository;
    private final HibernateSessionFactory hibernateSessionFactory;

    @Inject
    public RoomResource(RoomRepository roomRepository, HibernateSessionFactory hibernateSessionFactory)
    {
        this.roomRepository = roomRepository;
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RoomDto> getRooms()
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        Collection<Room> rooms = roomRepository.getAllRooms();
        Collection<RoomDto> roomDtos = RoomDto.create(rooms);

        session.getTransaction().commit();
        return roomDtos;
    }

    @GET
    @Path("/types")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RoomTypeDto> getRoomTypes()
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        Collection<RoomType> roomTypes = roomRepository.getAllRoomTypes();
        Collection<RoomTypeDto> roomTypeDtos = RoomTypeDto.create(roomTypes);

        // TODO fetch rooms from repository filtered by provided number of adults and number of children
        // TODO add comparable to Room and compare it by room names
        // TODO pass Set (so its ordered) to templatingContext instead of collection

        session.getTransaction().commit();
        return roomTypeDtos;
    }

    @GET
    @Path("stats")
    @Produces(MediaType.APPLICATION_JSON)
    public RoomStats getRoomStats()
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        final Collection<Room> rooms = roomRepository.getAllRooms();
        Collection<Room> available = Collections2.filter(rooms, new Predicate<Room>()
        {
            @Override
            public boolean apply(Room room)
            {
                return room.isAvailable();
            }
        });

        session.getTransaction().commit();
        return new RoomStats(available.size(), rooms.size() - available.size());
    }
}
