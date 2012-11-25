package net.mklew.hotelms.inhouse.web.dto.jaxb;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import net.mklew.hotelms.inhouse.web.dto.RoomDto;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/25/12
 *        time 7:30 PM
 */
public class RoomJAXBContext implements ContextResolver<JAXBContext>
{
    private JAXBContext context;

    public RoomJAXBContext() throws Exception
    {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        b.rootUnwrapping(true);
        b.arrays("roomdto");
        context = new JSONJAXBContext(b.build(), RoomDto.class);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType)
    {
        return objectType.equals(RoomDto.class) ? context : null;
    }
}
