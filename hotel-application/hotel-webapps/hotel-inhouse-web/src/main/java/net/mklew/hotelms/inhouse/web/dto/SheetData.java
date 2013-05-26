package net.mklew.hotelms.inhouse.web.dto;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Marek Lewandowski
 * @since 5/24/13
 */
public class SheetData
{
    private String roomName;

    private Collection<ReservationSheetData> reservations = Collections.emptyList();


    public SheetData()
    {
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public Collection<ReservationSheetData> getReservations()
    {
        return reservations;
    }

    public void setReservations(Collection<ReservationSheetData> reservations)
    {
        this.reservations = reservations;
    }
}
