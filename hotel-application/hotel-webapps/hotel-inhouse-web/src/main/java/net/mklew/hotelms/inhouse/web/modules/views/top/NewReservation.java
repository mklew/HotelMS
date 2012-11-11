package net.mklew.hotelms.inhouse.web.modules.views.top;

import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.domain.room.RoomType;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.i18n.I18nContext;
import org.objectledge.modules.views.BasicLedgeTopView;
import org.objectledge.parameters.Parameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;

import java.util.*;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 1:32 PM
 */
public class NewReservation extends BasicLedgeTopView
{
    private final RoomRepository roomRepository;
    private final HibernateSessionFactory hibernateSessionFactory;

    /**
     * Constructs a builder instance.
     *
     * @param context application context for use by this builder.
     * @param roomRepository
     * @param hibernateSessionFactory
     */
    public NewReservation(Context context, RoomRepository roomRepository, HibernateSessionFactory hibernateSessionFactory)
    {
        super(context);
        this.roomRepository = roomRepository;
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @Override
    public void process(Parameters parameters, TemplatingContext templatingContext, MVCContext mvcContext, HttpContext httpContext, I18nContext i18nContext) throws ProcessingException
    {
        Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        Collection<RoomType> roomTypes = roomRepository.getAllRoomTypes();
        templatingContext.put("roomTypes", roomTypes);

        // TODO fetch rooms from repository filtered by provided number of adults and number of children
        // TODO add comparable to Room and compare it by room names
        // TODO pass Set (so its ordered) to templatingContext instead of collection

        //Collection<Room> rooms = roomRepository.getAllRooms();
        //templatingContext.put("rooms", rooms);
        session.getTransaction().commit();
    }
}
