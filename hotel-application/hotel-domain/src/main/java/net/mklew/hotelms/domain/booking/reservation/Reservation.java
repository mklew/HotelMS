package net.mklew.hotelms.domain.booking.reservation;

import com.google.common.collect.Sets;
import net.mklew.hotelms.domain.booking.Id;
import net.mklew.hotelms.domain.booking.ReservationStatus;
import net.mklew.hotelms.domain.booking.reservation.rates.Rate;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.domain.room.Room;
import net.mklew.hotelms.domain.room.RoomId;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.*;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/2/12
 *        time 5:00 PM
 */
public class Reservation implements Serializable
{
    private Id reservationId;
    private Guest reservationOwner;
    private Set<Night> nights;
    private int numberOfAdults;
    private int numberOfChildren;
    private int extraBeds;
    private ReservationStatus reservationStatus;

    public Reservation(Id reservationId, Guest reservationOwner, Rate rate, DateTime checkIn,
                       DateTime checkOut, int numberOfAdults, int numberOfChildren, int extraBeds,
                       ReservationStatus reservationStatus)
    {
        this.reservationId = reservationId;
        this.reservationOwner = reservationOwner;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.extraBeds = extraBeds;
        this.reservationStatus = reservationStatus;
        this.nights = createNights(checkIn, checkOut, rate);
    }

    private Set<Night> createNights(DateTime checkIn, DateTime checkOut, Rate rate)
    {
        HashSet<Night> nights = new HashSet<>();
        for (int i = 0; !checkIn.plusDays(i).equals(checkOut.plusDays(1)); ++i)
        {
            nights.add(new Night(this, checkIn.plusDays(i), NightStatus.NOT_USED, rate.standardPrice(), rate));
        }
        return nights;
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
        return getCheckIn().toLocalDate().equals(date);
    }

    public boolean isCheckOut(LocalDate date)
    {
        return getCheckOut().toLocalDate().equals(date);
    }

    public List<Guest> listGuests()
    {
        // todo
        throw new NotImplementedException();
    }

    public void checkIn()
    {
        reservationStatus = ReservationStatus.INHOUSE;
    }

    public void checkOut()
    {
        reservationStatus = ReservationStatus.CHECKED_OUT;
    }

    public void cancel()
    {
        reservationStatus = ReservationStatus.CANCELLED;
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
        return (int) new Interval(getCheckIn(), getCheckOut()).toDuration().getStandardDays() + 1;
    }

    private ReservationStatus getStatus()
    {
        // todo implement this method
        throw new NotImplementedException();
    }

    // TODO current data structure allows for reservation to have more than one room but current implementation
    // limits it to just one
    public Room getRoom()
    {
        return nights.iterator().next().getRoom();
    }

    public DateTime getCheckIn()
    {
        SortedSet<DateTime> dates = getDates();
        return dates.first();
    }

    private SortedSet<DateTime> getDates()
    {
        SortedSet<DateTime> dates = new TreeSet<>();
        for (Night night : nights)
        {
            dates.add(night.getDate());
        }
        return dates;
    }

    public DateTime getCheckOut()
    {
        SortedSet<DateTime> dates = getDates();
        return dates.last();
    }

    public ReservationStatus getReservationStatus()
    {
        return reservationStatus;
    }

    public Guest getReservationOwner()
    {
        return reservationOwner;
    }

    public Rate getRate()
    {
        return nights.iterator().next().getRate();
    }

    public int getNumberOfAdults()
    {
        return numberOfAdults;
    }

    public int getNumberOfChildren()
    {
        return numberOfChildren;
    }

    public int getExtraBeds()
    {
        return extraBeds;
    }

    public Id getReservationId()
    {
        return reservationId;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (obj.getClass() != getClass())
        {
            return false;
        }
        Reservation rhs = (Reservation) obj;
        return new EqualsBuilder()
                .append(reservationId, rhs.reservationId)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(13, 47)
                .append(reservationId)
                .toHashCode();
    }

    public ReservationType getReservationType()
    {
        return ReservationType.SINGLE;
    }

    /**
     * Only for technical use
     *
     * @param reservationId
     */
    public void setReservationId(Id reservationId)
    {
        this.reservationId = reservationId;
    }

    private void setReservationOwner(Guest reservationOwner)
    {
        this.reservationOwner = reservationOwner;
    }

    private void setNights(Set<Night> nights)
    {
        this.nights = nights;
    }

    public void setNumberOfAdults(int numberOfAdults)
    {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(int numberOfChildren)
    {
        this.numberOfChildren = numberOfChildren;
    }

    public void setExtraBeds(int extraBeds)
    {
        this.extraBeds = extraBeds;
    }

    private void setReservationStatus(ReservationStatus reservationStatus)
    {
        this.reservationStatus = reservationStatus;
    }

    private Set<Night> getNights()
    {
        return nights;
    }

    Reservation()
    {
    }

    public void changeCheckInTo(DateTime newCheckIn)
    {
        DateTime checkout = getCheckOut();
        if (newCheckIn.isAfter(checkout))
        {
            throw new IllegalArgumentException("CheckIn" + newCheckIn + "cannot be after CheckOut");
        }
        else
        {
            changeCheckInAndCheckOut(newCheckIn, checkout);
        }
    }

    public void changeCheckOutTo(DateTime newCheckOut)
    {
        DateTime checkin = getCheckIn();
        if (newCheckOut.isBefore(checkin))
        {
            throw new IllegalArgumentException("CheckOut" + newCheckOut + "cannot be before checkIn");
        }
        else
        {
            changeCheckInAndCheckOut(checkin, newCheckOut);
        }
    }

    public void changeCheckInAndCheckOut(DateTime newCheckIn, DateTime newCheckOut)
    {
        if (newCheckIn.isAfter(newCheckOut))
        {
            throw new IllegalArgumentException("Wrong checkin" + newCheckIn + " and checkout " + newCheckOut + " " +
                    "dates");
        }
        Rate rate = getRate();
        final Set<Night> newNights = createNights(newCheckIn, newCheckOut, rate);
        nights.clear();
        nights.addAll(newNights);
    }

    public void changeRoom(Room room, Rate rate)
    {
        changeRate(rate);
    }

    public void changeRate(Rate chosenRate)
    {
        final DateTime checkIn = getCheckIn();
        final DateTime checkOut = getCheckOut();
        nights.clear();
        nights.addAll(createNights(checkIn, checkOut, chosenRate));
    }
}
