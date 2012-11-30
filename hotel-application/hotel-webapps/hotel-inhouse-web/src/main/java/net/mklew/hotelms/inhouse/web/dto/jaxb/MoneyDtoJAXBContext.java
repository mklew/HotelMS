package net.mklew.hotelms.inhouse.web.dto.jaxb;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import net.mklew.hotelms.inhouse.web.dto.MoneyDto;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/25/12
 *        time 1:26 PM
 */
@Provider
@Produces("application/json")
public class MoneyDtoJAXBContext implements ContextResolver<JAXBContext>
{
    private JAXBContext context;

    public MoneyDtoJAXBContext() throws Exception
    {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        b.rootUnwrapping(true);
        context = new JSONJAXBContext(b.build(), MoneyDto.class);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType)
    {
        return objectType.equals(MoneyDto.class) ? context : null;
    }
}