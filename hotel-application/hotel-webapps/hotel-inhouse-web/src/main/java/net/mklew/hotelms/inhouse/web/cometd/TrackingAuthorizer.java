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
    private static final String USERNAME = "username";

    @Override
    public boolean canHandshake(BayeuxServer server, ServerSession session, ServerMessage message)
    {
        if(session.isLocalSession())
            return true;
        if(!message.containsKey(USERNAME))
        {
            return false;
        }
        String username = (String) message.get(USERNAME);

        // TODO map username to userId and also both to ServerSession
        // TODO map should be available in some pico component with synchronized access

        session.addListener(new ServerSession.RemoveListener()
        {
            @Override
            public void removed(ServerSession serverSession, boolean b)
            {
                // TODO remove entry from map
            }
        });

        return true;
    }

    @Override
    public boolean canPublish(BayeuxServer server, ServerSession session, ServerChannel channel, ServerMessage message)
    {
        return super.canPublish(server, session, channel, message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
