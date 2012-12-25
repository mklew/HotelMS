package net.mklew.hotelms.domain.booking.reservation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 8:57 PM
 */
public enum ReservationType
{
    SINGLE
            {
                @Override
                public String getName()
                {
                    return "single";
                }
            },
    GROUP
            {
                @Override
                public String getName()
                {
                    return "group";
                }
            },
    CORPORATE
            {
                @Override
                public String getName()
                {
                    return "corporate";
                }
            },
    AGENT
            {
                @Override
                public String getName()
                {
                    return "agent";
                }
            };

    private final static Map<String, ReservationType> nameToType;

    static
    {
        nameToType = new HashMap<>(4);
        nameToType.put("single", SINGLE);
        nameToType.put("group", GROUP);
        nameToType.put("corporate", CORPORATE);
        nameToType.put("agent", AGENT);
    }

    public static ReservationType fromName(String name)
    {
        ReservationType reservationType = nameToType.get(name);
        if (reservationType == null)
        {
            throw new RuntimeException("Cannot resolve from name " + name);
        }
        return reservationType;
    }

    public abstract String getName();
}
