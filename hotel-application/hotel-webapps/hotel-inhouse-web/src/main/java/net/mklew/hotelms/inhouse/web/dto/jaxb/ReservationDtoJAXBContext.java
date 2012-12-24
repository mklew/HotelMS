package net.mklew.hotelms.inhouse.web.dto.jaxb;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import net.mklew.hotelms.inhouse.web.dto.ReservationDto;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 3:54 PM
 */
@Provider
@Produces("application/json")
public class ReservationDtoJAXBContext implements ContextResolver<JAXBContext>
{
    private JAXBContext context;

    public ReservationDtoJAXBContext() throws Exception
    {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        b.rootUnwrapping(true);
        b.arrays("reservationdto");
        context = new JSONJAXBContext(b.build(), ReservationDto.class);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType)
    {
        return objectType.equals(ReservationDto.class) ? context : null;
    }
}
