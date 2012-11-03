package net.mklew.hotelms.inhouse.web.modules.views.data;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.booking.reservation.rates.RateRepository;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomType;
import org.codehaus.jackson.JsonGenerationException;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.parameters.Parameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.web.json.AbstractJsonView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 8:54 PM
 */
public class RoomData extends AbstractJsonView
{
    private final RateRepository rateRepository;

    /**
     * Creates an new AbstractJsonView instnace.
     *
     * @param context the request context.
     * @param log     the logger.
     */
    public RoomData(Context context, Logger log, RateRepository rateRepository)
    {
        super(context, log);
        this.rateRepository = rateRepository;
    }

    @Override
    protected void buildJsonStream() throws ProcessingException, JsonGenerationException, IOException
    {
        final Parameters params = getRequestParameters();
        final String roomName = params.get("room");
        // TODO fetch room by repository
        RoomType luxury = new RoomType("luxury");
        Room room = new Room(luxury, new RoomName("100", "L"), 1);

        Collection<Rate> rates = rateRepository.getAllRatesForRoom(room);
        // TODO convert rates to json
        super.buildJsonStream();    //To change body of overridden methods use File | Settings | File Templates.
    }


}
