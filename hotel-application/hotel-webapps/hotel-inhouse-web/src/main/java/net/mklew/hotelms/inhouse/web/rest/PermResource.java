package net.mklew.hotelms.inhouse.web.rest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 1/1/13
 *        time 1:48 AM
 */
@Path("perm")
public class PermResource
{

    @GET
    public Response hasPerm(@QueryParam("perm") String permission)
    {
        final Subject subject = SecurityUtils.getSubject();
        final boolean permitted = subject.isPermitted(permission);
        if (permitted)
        {
            return Response.ok().build();
        }
        else
        {
            return Response.ok().status(403).build();
        }
    }
}
