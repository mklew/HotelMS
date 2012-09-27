package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.booking.reservation.Night;
import net.mklew.hotelms.domain.booking.reservation.rates.inclusion.Inclusion;
import net.mklew.hotelms.domain.policy.Policy;

import java.util.List;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:01 PM
 */
public class Package
{
    private List<AvailabilityPeriod> timePeriods;
    private List<Night> nights;
    private List<Inclusion> inclusions;
    private List<Policy> policies;
    private List<PackageRate> rates;

    public int lengthOfStay()
    {
        return nights.size();
    }

}
