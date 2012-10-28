package net.mklew.hotelms.inhouse.web.cometd;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.DefaultSecurityPolicy;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/28/12
 *        Time: 8:56 PM
 */
public class TrackingAuthorizer extends DefaultSecurityPolicy
{
    @Override
    public boolean canHandshake(BayeuxServer server, ServerSession session, ServerMessage message)
    {
        return super.canHandshake(server, session, message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean canPublish(BayeuxServer server, ServerSession session, ServerChannel channel, ServerMessage message)
    {
        return super.canPublish(server, session, channel, message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
