package net.mklew.hotelms.domain.guests;

import java.io.Serializable;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/1/12
 *        time 3:38 PM
 */
public class Country implements Serializable
{
    private long id;
    private String iso1Code;
    private String nameCaps;
    private String name;
    private String iso3Code;
    private int numCode;

    Country()
    {
    }

    public Country(String iso1Code, String nameCaps, String name, String iso3Code, int numCode)
    {
        this.id = id;
        this.iso1Code = iso1Code;
        this.nameCaps = nameCaps;
        this.name = name;
        this.iso3Code = iso3Code;
        this.numCode = numCode;
    }

    public long getId()
    {
        return id;
    }

    void setId(long id)
    {
        this.id = id;
    }

    public String getIso1Code()
    {
        return iso1Code;
    }

    void setIso1Code(String iso1Code)
    {
        this.iso1Code = iso1Code;
    }

    public String getNameCaps()
    {
        return nameCaps;
    }

    void setNameCaps(String nameCaps)
    {
        this.nameCaps = nameCaps;
    }

    public String getName()
    {
        return name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    public String getIso3Code()
    {
        return iso3Code;
    }

    void setIso3Code(String iso3Code)
    {
        this.iso3Code = iso3Code;
    }

    public int getNumCode()
    {
        return numCode;
    }

    void setNumCode(int numCode)
    {
        this.numCode = numCode;
    }
}
