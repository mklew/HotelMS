package net.mklew.hotelms.inhouse.web.rest;

import com.sun.jersey.spi.resource.Singleton;
import net.mklew.hotelms.inhouse.web.rest.test.HelloComponent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/21/12
 *        Time: 1:27 PM
 */
@Singleton
@Path("/hello")
public class HelloRest
{
    private HelloComponent helloComponent;

    public HelloRest(HelloComponent helloComponent)
    {
        this.helloComponent = helloComponent;
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey " + helloComponent.saySomething();
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey " + helloComponent.saySomething() + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey " + helloComponent.saySomething() + "</body></h1>" + "</html> ";
    }

}