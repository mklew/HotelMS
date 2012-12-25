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
    MALE, FEMALE, OTHER, REFUSED;

    private static Map<String, Gender> namesToGender;

    static
    {
        namesToGender = new HashMap<>(4);
        namesToGender.put("male", MALE);
        namesToGender.put("female", FEMALE);
        namesToGender.put("other", OTHER);
        namesToGender.put("refused", REFUSED);
    }

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
