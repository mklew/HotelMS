package net.mklew.hotelms.inhouse.web.rest;

import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.domain.booking.GuestRepository;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;
import net.mklew.hotelms.persistance.hibernate.configuration.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Collection;

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
    public Collection<GuestDto> byCommonName(@QueryParam("q") String query)
    {
        final Session session = hibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        // remove asterisk from query
        String commonName = query.substring(0, query.length() - 2);

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
