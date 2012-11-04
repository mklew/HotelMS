package net.mklew.hotelms.domain.booking;

/**
 * Status of reservation
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/9/12
 * time 9:04 PM
 */
public enum ReservationStatus
{
    // todo revisit history status, maybe rename it to something more appropriate
    /**
     * Checkin status means that today is the day of check-in
     */
    CHECKIN,
    /**
     * Checkout status means that today is the day of check-out
     */
    CHECKOUT,
    /**
     * In house reservation is checked-in reservation and during stay of guest/guests.
     */
    INHOUSE,
    /**
     * History reservation is the ending status of reservation usual lifecycle
     */
    HISTORY,
    /**
     * Cancelled reservation by guest or other authority
     */
    CANCELLED,
    /**
     * Reserved reservation is the one which is valid and waits for the day of check-in
     */
    RESERVED,
    /**
     *  No show is when guest has not checked-in in check-in day
     */
    NOSHOW,
    /**
     * Temporary reservation is the one which is valid only to some hour, for example to 4 pm day it's made
     */
    TEMPORARY


}
