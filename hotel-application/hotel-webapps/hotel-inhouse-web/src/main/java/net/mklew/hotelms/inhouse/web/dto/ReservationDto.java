package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.inhouse.web.dto.dates.DateParser;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 3:48 PM
 */
@XmlRootElement(name = "reservationdto")
public class ReservationDto
{
    private String reservationType;
    private String checkin;
    private String checkout;
    private String numberOfAdults;
    private String numberOfChildren;
    private String roomType;
    private String roomName;
    private String roomExtraBed;
    private String rateType;

    @JsonIgnore
    private transient DateTime checkinDate;
    @JsonIgnore
    private transient DateTime checkoutDate;

    public static ReservationDto fromReservationForm(MultivaluedMap<String, String> formParams)
    {
        ReservationDto dto = new ReservationDto();
        // required
        dto.reservationType = formParams.getFirst("reservationType");
        dto.checkin = formParams.getFirst("checkin");
        dto.checkout = formParams.getFirst("checkout");
        dto.numberOfAdults = formParams.getFirst("numberOfAdults");
        dto.numberOfChildren = formParams.getFirst("numberOfChildren");
        dto.roomType = formParams.getFirst("roomType");
        dto.roomName = formParams.getFirst("roomName");
        dto.roomExtraBed = formParams.getFirst("roomExtraBed");
        dto.rateType = formParams.getFirst("rateType");

        // optional
        // none so far
        dto.checkinDate = DateParser.fromString(formParams.getFirst("checkin"));
        dto.checkoutDate = DateParser.fromString(formParams.getFirst("checkout"));

        return dto;
    }

    public String getReservationType()
    {
        return reservationType;
    }

    public void setReservationType(String reservationType)
    {
        this.reservationType = reservationType;
    }

    public String getCheckin()
    {
        return checkin;
    }

    public void setCheckin(String checkin)
    {
        this.checkin = checkin;
    }

    public String getCheckout()
    {
        return checkout;
    }

    public void setCheckout(String checkout)
    {
        this.checkout = checkout;
    }

    public String getNumberOfAdults()
    {
        return numberOfAdults;
    }

    public void setNumberOfAdults(String numberOfAdults)
    {
        this.numberOfAdults = numberOfAdults;
    }

    public String getNumberOfChildren()
    {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren)
    {
        this.numberOfChildren = numberOfChildren;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType(String roomType)
    {
        this.roomType = roomType;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public String getRoomExtraBed()
    {
        return roomExtraBed;
    }

    public void setRoomExtraBed(String roomExtraBed)
    {
        this.roomExtraBed = roomExtraBed;
    }

    public String getRateType()
    {
        return rateType;
    }

    public void setRateType(String rateType)
    {
        this.rateType = rateType;
    }
}
