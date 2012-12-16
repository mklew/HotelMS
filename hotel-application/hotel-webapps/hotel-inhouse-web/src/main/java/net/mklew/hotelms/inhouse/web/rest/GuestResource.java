package net.mklew.hotelms.inhouse.web.rest;

import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 7:44 PM
 */
@Singleton
@Path("guests")
public class GuestResource
{
    private final HibernateSessionFactory hibernateSessionFactory;
    private final GuestRepository guestRepository;

    public GuestResource(HibernateSessionFactory hibernateSessionFactory, GuestRepository guestRepository)
    {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.guestRepository = guestRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<GuestDto> byCommonName(@QueryParam("q") String query)
    {
        if (query == null)
        {
            return Collections.emptyList();
        }
        final Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        String commonName = query;

        // split on space and get firstname and possibly surname
        final String[] split = commonName.split(" ");
        final String firstName = split[0];
        String surname = "";
        if (split.length > 1)
        {
            surname = split[1];
        }

        final Collection<Guest> guests = guestRepository.findAllWhereNameLike(firstName, surname);
        final Collection<GuestDto> guestDtos = GuestDto.fromGuests(guests);

        session.getTransaction().commit();
        return guestDtos;
    }
}
