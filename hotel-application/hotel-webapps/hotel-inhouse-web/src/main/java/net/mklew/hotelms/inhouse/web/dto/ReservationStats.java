package net.mklew.hotelms.inhouse.web.dto;

/**
 * @author Marek Lewandowski
 * @since 5/23/13
 */
public class ReservationStats
{
    private int inhouse;

    private int checkin;

    private int checkout;

    public ReservationStats()
    {
    }

    public ReservationStats(int inhouse, int checkin, int checkout)
    {
        this.inhouse = inhouse;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getInhouse()
    {
        return inhouse;
    }

    public void setInhouse(int inhouse)
    {
        this.inhouse = inhouse;
    }

    public int getCheckin()
    {
        return checkin;
    }

    public void setCheckin(int checkin)
    {
        this.checkin = checkin;
    }

    public int getCheckout()
    {
        return checkout;
    }

    public void setCheckout(int checkout)
    {
        this.checkout = checkout;
    }
}
