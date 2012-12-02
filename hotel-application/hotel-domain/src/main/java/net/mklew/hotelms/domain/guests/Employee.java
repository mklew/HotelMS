package net.mklew.hotelms.domain.guests;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/1/12
 *        time 6:03 PM
 */
public class Employee extends Person
{
    private String identifier;

    Employee()
    {
    }



    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }
}
