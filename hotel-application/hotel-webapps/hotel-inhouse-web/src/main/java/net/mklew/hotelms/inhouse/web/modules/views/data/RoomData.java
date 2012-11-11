package net.mklew.hotelms.inhouse.web.modules.views.data;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.booking.reservation.rates.RateRepository;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomRepository;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.parameters.Parameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.web.json.AbstractJsonView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 8:54 PM
 */
public class RoomData extends AbstractJsonView
{
    private final RateRepository rateRepository;
    private final HibernateSessionFactory sessionFactory;
    private final RoomRepository roomRepository;

    /**
     * Creates an new AbstractJsonView instnace.
     *
     * @param context the request context.
     * @param log     the logger.
     */
    public RoomData(Context context, Logger log, RateRepository rateRepository, HibernateSessionFactory sessionFactory, RoomRepository roomRepository)
    {
        super(context, log);
        this.rateRepository = rateRepository;
        this.sessionFactory = sessionFactory;
        this.roomRepository = roomRepository;
    }

    @Override
    protected void buildJsonStream() throws ProcessingException, JsonGenerationException, IOException
    {
        final Parameters params = getRequestParameters();
        String roomName = params.get("room");
        // TODO handle situation where there is no room
        // TODO return rates only when rates parameter is defined
        // TODO error handling
        roomName = roomName.substring(roomName.indexOf(RoomName.DELIMETER) + 1);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Room room = roomRepository.getRoomByName(new RoomName(roomName));
        Collection<Rate> rates = rateRepository.getAllRatesForRoom(room);
        session.getTransaction().commit();

        // TODO fetch room by repository
        // TODO convert rates to json
        ObjectMapper mapper = new ObjectMapper();

        Collection<Map<String,Object>> ratesWithNames = new ArrayList<>();
        for(Rate rate : rates)
        {
            final Map<String, Object> rateMap = new HashMap<>();
            rateMap.put("rateName", rate.getRateName());
            rateMap.put("rate", rate);
            ratesWithNames.add(rateMap);
        }
        String ratesJSON = mapper.writeValueAsString(ratesWithNames);
        log.debug("Created JSON rates and it is as follows:");
        log.debug(ratesJSON);
        jsonGenerator.writeRaw(ratesJSON);

    }


}
