package net.mklew.hotelms.inhouse.web.dto.jaxb;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import net.mklew.hotelms.inhouse.web.dto.ErrorDto;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:14 PM
 */
public class ErrorDtoJAXBContext implements ContextResolver<JAXBContext>
{
    private JAXBContext context;

    public ErrorDtoJAXBContext() throws Exception
    {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        b.rootUnwrapping(true);
        b.arrays("errordto");
        context = new JSONJAXBContext(b.build(), ErrorDto.class);
    }

    @Override
    public JAXBContext getContext(Class<?> type)
    {
        return type.equals(ErrorDto.class) ? context : null;
    }
}
