package net.mklew.hotelms.domain.guests;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12 time 8:44 PM
 */
public enum Gender
{
    MALE("male"), FEMALE("female"), OTHER("other"), REFUSED("refused");


    private static Map<String, Gender> namesToGender;

    static
    {
        namesToGender = new HashMap<>(Gender.values().length);
        for(Gender gender : Gender.values())
        {
            namesToGender.put(gender.getName(), gender);
        }
    }

    private String name;

    public String getName()
    {
        return name;
    }

    private Gender(String name)
    {
        this.name = name;
    }

    public static Gender fromName(String name)
    {
        Gender gender = namesToGender.get(name);
        if(gender == null)
        {
            throw new RuntimeException("Cannot resolve Gender from name: " + name);
        }
        return gender;
    }
}
