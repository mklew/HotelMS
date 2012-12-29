package net.mklew.hotelms.domain.guests;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:44 PM
 */
public enum Gender
{
    MALE
            {
                @Override
                public String getName()
                {
                    return "male";
                }
            }, FEMALE
        {
            @Override
            public String getName()
            {
                return "female";
            }
        }, OTHER
        {
            @Override
            public String getName()
            {
                return "other";
            }
        }, REFUSED
        {
            @Override
            public String getName()
            {
                return "refused";
            }
        };

    private static Map<String, Gender> namesToGender;

    static
    {
        namesToGender = new HashMap<>(Gender.values().length);
        for (Gender gender : Gender.values())
        {
            namesToGender.put(gender.getName(), gender);
        }
    }

    public abstract String getName();

    public static Gender fromName(String name)
    {
        Gender gender = namesToGender.get(name);
        if (gender == null)
        {
            throw new RuntimeException("Cannot resolve Gender from name: " + name);
        }
        return gender;
    }
}
