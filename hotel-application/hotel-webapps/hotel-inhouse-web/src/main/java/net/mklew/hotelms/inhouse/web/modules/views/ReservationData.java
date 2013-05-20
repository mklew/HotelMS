package net.mklew.hotelms.inhouse.web.modules.views;

import com.fasterxml.jackson.core.JsonGenerationException;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.web.json.AbstractJsonView;

import java.io.IOException;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/25/12
 *        Time: 10:40 PM
 */
public class ReservationData extends AbstractJsonView
{
    /**
     * Creates an new AbstractJsonView instnace.
     *
     * @param context the request context.
     * @param log     the logger.
     */
    public ReservationData(Context context, Logger log)
    {
        super(context, log);
    }

    @Override
    protected void buildJsonStream() throws ProcessingException, JsonGenerationException, IOException
    {
        jsonGenerator.writeRaw("{\"from\":\"2012-08-01\",\"to\":\"2012-08-15\",\"reservations\":[{\"room\":\"R1\",\"reservations\":[{\"firstName\":\"Kasia\",\"lastName\":\"Kowalska\",\"reservationNumber\":\"12345678\",\"status\":\"history\",\"from\":\"2012-08-01\",\"to\":\"2012-08-03\"},{\"firstName\":\"John\",\"lastName\":\"Doe\",\"reservationNumber\":\"12345679\",\"status\":\"checkout\",\"from\":\"2012-08-02\",\"to\":\"2012-08-04\"}]},{\"room\":\"R2\",\"reservations\":[{\"firstName\":\"Michal\",\"lastName\":\"Aniol\",\"reservationNumber\":\"12345610\",\"status\":\"checkin\",\"from\":\"2012-08-04\",\"to\":\"2012-08-08\"}]},{\"room\":\"R3\",\"reservations\":[{\"firstName\":\"Eric\",\"lastName\":\"Evans\",\"reservationNumber\":\"12345333\",\"status\":\"inhouse\",\"from\":\"2012-07-25\",\"to\":\"2012-08-12\"}]},{\"room\":\"R4\",\"reservations\":[]},{\"room\":\"R5\",\"reservations\":[{\"firstName\":\"Tom\",\"lastName\":\"Hanks\",\"reservationNumber\":\"12312333\",\"status\":\"checkin\",\"from\":\"2012-08-04\",\"to\":\"2012-08-06\"},{\"firstName\":\"John\",\"lastName\":\"Lock\",\"reservationNumber\":\"123123333\",\"status\":\"reserved\",\"from\":\"2012-08-07\",\"to\":\"2012-08-16\"}]},{\"room\":\"R6\",\"reservations\":[{\"firstName\":\"Jan\",\"lastName\":\"Janowski\",\"reservationNumber\":\"12345333\",\"status\":\"inhouse\",\"from\":\"2012-07-25\",\"to\":\"2012-08-12\"}]},{\"room\":\"R7\",\"reservations\":[{\"firstName\":\"Morgan\",\"lastName\":\"Freeman\",\"reservationNumber\":\"12341341\",\"status\":\"reserved\",\"from\":\"2012-08-06\",\"to\":\"2012-08-10\"}]}]}");
    }
}
