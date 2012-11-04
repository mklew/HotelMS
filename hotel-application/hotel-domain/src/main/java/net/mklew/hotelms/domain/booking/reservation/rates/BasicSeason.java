package net.mklew.hotelms.domain.booking.reservation.rates;

import org.joda.time.DateTime;

/**
 * Represents named season.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 2:11 PM
 * @see SeasonRate
 */
public class BasicSeason implements Season
{
    private Long id; // for Hibernate

    private AvailabilityPeriod period;
    private String name;

    public BasicSeason(String name, AvailabilityPeriod period)
    {
        this.name = name;
        this.period = period;
    }

    @Override
    public AvailabilityPeriod getSeason()
    {
        return period;
    }

    @Override
    public String getSeasonName()
    {
        return name;
    }

    @Override
    public boolean isAvailableIn(DateTime time)
    {
        return period.isAvailableIn(time);
    }

    @Override
    public boolean isAvailableNow()
    {
        return period.isAvailableNow();
    }

    @Override
    public boolean isActive()
    {
        return period.isActive();

    }

    // hibernate

    AvailabilityPeriod getPeriod()
    {
        return period;
    }

    String getName()
    {
        return name;
    }

    // private for hibernate
    private Long getId()
    {
        return id;
    }
    // private setters for Hibernate
    private void setId(Long id)
    {
        this.id = id;
    }

    private void setPeriod(AvailabilityPeriod period)
    {
        this.period = period;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    BasicSeason()
    {
        period = null;
        name = null;
        // for hibernate
    }
}
