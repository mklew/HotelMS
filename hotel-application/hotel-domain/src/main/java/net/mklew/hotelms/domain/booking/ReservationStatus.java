package net.mklew.hotelms.domain.booking;

/**
 * Status of reservation
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/9/12
 *        time 9:04 PM
 */
public enum ReservationStatus
{
    // todo revisit history status, maybe rename it to something more appropriate
    /**
     * Checkin status means that today is the day of check-in
     */
    CHECKIN
            {
                @Override
                public String getName()
                {
                    return "checkin";
                }
            },
    /**
     * Checkout status means that today is the day of check-out
     */
    CHECKOUT
            {
                @Override
                public String getName()
                {
                    return "checkout";
                }
            },
    /**
     * In house reservation is checked-in reservation and during stay of guest/guests.
     */
    INHOUSE
            {
                @Override
                public String getName()
                {
                    return "inhouse";
                }
            },
    /**
     * History reservation is the ending status of reservation usual lifecycle
     */
    HISTORY
            {
                @Override
                public String getName()
                {
                    return "history";
                }
            },
    /**
     * Cancelled reservation by guest or other authority
     */
    CANCELLED
            {
                @Override
                public String getName()
                {
                    return "cancelled";
                }
            },
    /**
     * Reserved reservation is the one which is valid and waits for the day of check-in
     */
    RESERVED
            {
                @Override
                public String getName()
                {
                    return "reserved";
                }
            },
    /**
     * No show is when guest has not checked-in in check-in day
     */
    NOSHOW
            {
                @Override
                public String getName()
                {
                    return "noshow";
                }
            },
    /**
     * Temporary reservation is the one which is valid only to some hour, for example to 4 pm day it's made
     */
    TEMPORARY
            {
                @Override
                public String getName()
                {
                    return "temporary";
                }
            },
    /**
     * Check-out is the state which is supposed to be triggered automatically when day of the checkout comes. CheckedOut
     * is the status of the reservation that actually was checked out and guest left hotel.
     */
    CHECKED_OUT
            {
                @Override
                public String getName()
                {
                    return "checkedout";
                }
            },

    TECHNICAL
            {
                @Override
                public String getName()
                {
                    return "technical";
                }
            };

    public abstract String getName();

    public ReservationStatus fromName(String name)
    {
        for (ReservationStatus status : values())
        {
            if (status.getName().equals(name))
            {
                return status;
            }
        }
        throw new RuntimeException("No ReservationStatus with name: " + name + " was found");
    }
}
