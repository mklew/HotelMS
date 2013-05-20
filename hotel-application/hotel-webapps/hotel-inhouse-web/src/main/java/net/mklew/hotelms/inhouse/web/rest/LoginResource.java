package net.mklew.hotelms.inhouse.web.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/31/12
 *        time 3:12 PM
 */
@Path("login")
public class LoginResource
{
    /**
     * This is only something like ping or loopback. Credentials are checked using BasicHttpAuthenticationFilter by
     * Shiro
     */
    @POST
    public Response login()
    {
        return Response.ok().build();
    }
}
