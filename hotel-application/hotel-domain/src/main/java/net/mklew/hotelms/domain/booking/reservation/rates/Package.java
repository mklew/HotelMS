package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.booking.reservation.Night;
import net.mklew.hotelms.domain.booking.reservation.rates.inclusion.Inclusion;
import net.mklew.hotelms.domain.policy.Policy;

import java.util.List;
import java.util.Set;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:01 PM
 */
public class Package
{
    private Long id;
    private List<AvailableIn> timePeriods;
    private List<Night> nights;
    private List<Inclusion> inclusions;
    private List<Policy> policies;
    private Set<PackageRate> rates;
    private String name;

    public int lengthOfStay()
    {
        return nights.size();
    }

    public String getPackageName()
    {
        return name;
    }

    public Set<PackageRate> getRates()
    {
        return rates;
    }

    // hibernate
    private Long getId()
    {
        return id;
    }

    // hibernate
    private void setId(Long id)
    {
        this.id = id;
    }

    // hibernate
    private String getName()
    {
        return name;
    }

    // hibernate
    private void setName(String name)
    {
        this.name = name;
    }

    // hibernate
    private void setRates(Set<PackageRate> rates)
    {
        this.rates = rates;
    }

    // hibernate
    Package()
    {
    }
}
