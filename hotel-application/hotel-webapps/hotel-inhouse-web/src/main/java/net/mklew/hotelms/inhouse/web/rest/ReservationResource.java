package net.mklew.hotelms.inhouse.web.rest;

import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;
import net.mklew.hotelms.inhouse.web.dto.MissingGuestInformation;
import net.mklew.hotelms.inhouse.web.dto.ReservationDto;
import org.jcontainer.dna.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 3:46 PM
 */
@Singleton
@Path("/reservations")
public class ReservationResource
{
    private final Logger logger;

    public ReservationResource(Logger logger)
    {
        this.logger = logger;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ReservationDto> getAllReservations()
    {
        // todo
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ReservationDto createNewReservation(MultivaluedMap<String, String> formParams)
    {
        logger.debug(formParams.toString());
        try
        {
            GuestDto reservationOwner = GuestDto.fromReservationForm(formParams);
        } catch (MissingGuestInformation missingGuestInformation)
        {
            logger.error("Reservation owner has no sufficient information", missingGuestInformation);
            return null;
        }
        return null;
    }
}
