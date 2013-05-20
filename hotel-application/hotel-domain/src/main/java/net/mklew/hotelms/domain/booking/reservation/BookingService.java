package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.room.RoomName;
import net.mklew.hotelms.domain.room.RoomNotFoundException;
import org.joda.time.DateTime;

/**
 * @author Marek Lewandowski
 * @since 5/20/13
 */
public interface BookingService
{
    boolean isRoomAvailable(Reservation reservation);

    void bookReservation(Reservation reservation) throws RoomIsUnavailableException;

    void changeRate(Reservation reservation, String chosenRate) throws RateNotFoundException;

    void rebookReservationRoom(Reservation reservation, RoomName roomName, String newRate,
                               DateTime newCheckIn, DateTime
            newCheckOut) throws RoomNotFoundException, RateNotFoundException, RoomIsUnavailableException;

    void rebookVisit(Reservation reservation, DateTime newCheckIn,
                     DateTime newCheckOut) throws RoomIsUnavailableException;
}
