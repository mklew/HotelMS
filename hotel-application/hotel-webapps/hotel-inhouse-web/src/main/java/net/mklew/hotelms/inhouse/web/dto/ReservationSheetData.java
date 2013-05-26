package net.mklew.hotelms.inhouse.web.dto;

/**
 * @author Marek Lewandowski
 * @since 5/24/13
 */
public class ReservationSheetData
{
    private String firstName;

    private String lastName;

    private String reservationNumber;

    private String status;

    /**
     * format "YYYY-MM-DD"
     */
    private String from;

    private String to;

    public ReservationSheetData()
    {
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getReservationNumber()
    {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber)
    {
        this.reservationNumber = reservationNumber;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }
}
