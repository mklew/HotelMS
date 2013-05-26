package net.mklew.hotelms.inhouse.web.dto;

import java.util.Collection;

/**
 * @author Marek Lewandowski
 * @since 5/26/13
 */
public class AuditResults
{
    private Collection<ReservationDto> noShows;
    private Collection<ReservationDto> tommorrowCheckIns;

    public void setNoShows(Collection<ReservationDto> noShows)
    {
        this.noShows = noShows;
    }

    public Collection<ReservationDto> getNoShows()
    {
        return noShows;
    }

    public void setTommorrowCheckIns(Collection<ReservationDto> tommorrowCheckIns)
    {
        this.tommorrowCheckIns = tommorrowCheckIns;
    }

    public Collection<ReservationDto> getTommorrowCheckIns()
    {
        return tommorrowCheckIns;
    }
}
