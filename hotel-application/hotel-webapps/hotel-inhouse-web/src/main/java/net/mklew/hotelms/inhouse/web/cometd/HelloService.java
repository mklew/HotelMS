package net.mklew.hotelms.inhouse.web.cometd;

import java.util.Map;
import java.util.HashMap;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/28/12
 *        Time: 5:50 PM
 */
public class HelloService extends AbstractService
{
    public HelloService(BayeuxServer bayeux)
    {
        super(bayeux, "hello");
        addService("/service/hello", "processHello");
    }

    public void processHello(ServerSession remote, Message message)
    {
        Map<String, Object> input = message.getDataAsMap();
        String name = (String) input.get("name");

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("greeting", "Hello, " + name);
        remote.deliver(getServerSession(), "/hello", output, null);
    }
}

