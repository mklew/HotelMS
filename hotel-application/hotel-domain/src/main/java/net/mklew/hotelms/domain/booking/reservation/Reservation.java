package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomId;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/2/12
 * time 5:00 PM
 */
public class Reservation
{
    private Interval stay;
    private ReservationId reservationId;
    private Guest reservationOwner;
    private Room room;
    private Rate rate;
    private DateTime checkIn;
    private DateTime checkOut;
    private int numberOfAdults;
    private int numberOfChildren;
    private int extraBeds;
    private ReservationStatus reservationStatus;

    public Reservation(Interval stay)
    {
        this.stay = stay;
    }

    public Reservation(ReservationId reservationId, Guest reservationOwner, Room room, Rate rate, DateTime checkIn,
                       DateTime checkOut, int numberOfAdults, int numberOfChildren, int extraBeds,
                       ReservationStatus reservationStatus)
    {
        this.reservationId = reservationId;
        this.reservationOwner = reservationOwner;
        this.room = room;
        this.rate = rate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.extraBeds = extraBeds;
        this.reservationStatus = reservationStatus;
    }

    public void moveToRoom(RoomId roomId)
    {
        // todo if checked in then throw exception that reservation needs to be split first
        throw new NotImplementedException();
    }

    public List<Reservation> split(DateTime date)
    {
        // todo
        throw new NotImplementedException();
    }

    /**
     * Changes reservation status to reserved
     */
    public void reserve()
    {
        reservationStatus = ReservationStatus.RESERVED;
    }

    public boolean isCheckIn(LocalDate date)
    {
        return stay.getStart().toLocalDate().equals(date);
    }

    public boolean isCheckIn()
    {
        return isCheckIn(LocalDate.now());
    }

    public boolean isCheckOut(LocalDate date)
    {
        return stay.getEnd().toLocalDate().equals(date);
    }

    public boolean isCheckOut()
    {
        return isCheckOut(LocalDate.now());
    }

    public List<Guest> listGuests()
    {
        // todo
        throw new NotImplementedException();
    }

    public void checkIn()
    {
        // todo
        throw new NotImplementedException();
    }

    public void checkOut()
    {
        // todo check if its not early checkout according and do it right according to policy about early checkouts,
        // change status of reservation
        throw new NotImplementedException();
    }

    public void addGuest(Guest guest)
    {
        // todo
        throw new NotImplementedException();
    }

    public void removeGuest(Guest guest)
    {
        // todo
        throw new NotImplementedException();
    }

    public void doAudit()
    {
        // todo perform night audit of reservation and change its status accordingly
        // todo this method could emit events in case of situations like NoShow
        throw new NotImplementedException();
    }

    public int lengthOfStay()
    {
        return (int) stay.toDuration().getStandardDays() + 1;
    }

    private ReservationStatus getStatus()
    {
        // todo implement this method
        throw new NotImplementedException();
    }

    public Room getRoom()
    {
        return room;
    }

    public DateTime getCheckIn()
    {
        return checkIn;
    }

    public DateTime getCheckOut()
    {
        return checkOut;
    }

    public ReservationStatus getReservationStatus()
    {
        return reservationStatus;
    }
}
