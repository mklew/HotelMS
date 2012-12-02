package net.mklew.hotelms.inhouse.web.dto.jaxb;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import net.mklew.hotelms.inhouse.web.dto.GuestDto;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:14 PM
 */
public class GuestDtoJAXBContext implements ContextResolver<JAXBContext>
{
    private JAXBContext context;

    public GuestDtoJAXBContext() throws Exception
    {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        b.rootUnwrapping(true);
        b.arrays("guestdto");
        context = new JSONJAXBContext(b.build(), GuestDto.class);
    }

    @Override
    public JAXBContext getContext(Class<?> type)
    {
        return type.equals(GuestDto.class) ? context : null;
    }
}
