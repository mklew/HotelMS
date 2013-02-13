package net.mklew.hotelms.inhouse.web.dto.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Marek Lewandowski
 * @since 2/13/13
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GenericObjectMapperProvider
    implements ContextResolver<ObjectMapper>
{

    private final ObjectMapper objectMapper;

    public GenericObjectMapperProvider()
    {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type)
    {
        return objectMapper;
    }

}
