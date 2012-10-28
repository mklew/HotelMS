package net.mklew.hotelms.inhouse.web.cometd;

import java.util.Map;
import java.util.HashMap;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;
import org.jcontainer.dna.Logger;
import org.objectledge.cometd.BayeuxService;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/28/12
 *        Time: 5:50 PM
 */
public class HelloService extends BayeuxService
{
    private final Logger logger;

    public HelloService(BayeuxServer bayeux, Logger logger)
    {
        super(bayeux, "hello");
        this.logger = logger;
        addService("/service/hello", "processHello");
    }

    public void processHello(ServerSession remote, Message message)
    {
        logger.debug("Processing message: "+ message);
        Map<String, Object> input = message.getDataAsMap();
        String name = (String) input.get("name");

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("greeting", "Hello, " + name);
        remote.deliver(getServerSession(), "/hello", output, null);
    }
}

