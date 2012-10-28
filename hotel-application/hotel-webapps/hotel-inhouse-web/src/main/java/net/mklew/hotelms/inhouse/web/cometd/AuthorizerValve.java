package net.mklew.hotelms.inhouse.web.cometd;

import org.cometd.bayeux.server.BayeuxServer;
import org.objectledge.cometd.BayeuxServerValve;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/28/12
 *        Time: 5:45 PM
 */
public class AuthorizerValve implements BayeuxServerValve
{
    @Override
    public void process(BayeuxServer bayeuxServer)
    {
        bayeuxServer.setSecurityPolicy(new TrackingAuthorizer());
    }
}