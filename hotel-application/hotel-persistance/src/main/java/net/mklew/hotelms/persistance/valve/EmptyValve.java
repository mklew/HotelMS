package net.mklew.hotelms.persistance.valve;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 12/23/12
 * time 11:27 PM
 */
public class EmptyValve implements Valve
{
    @Override
    public void process(Context context) throws ProcessingException
    {

    }
}
