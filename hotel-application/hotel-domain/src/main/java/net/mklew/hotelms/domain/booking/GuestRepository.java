package net.mklew.hotelms.domain.booking;

import net.mklew.hotelms.domain.guests.Guest;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 7:49 PM
 */
public interface GuestRepository
{
    Collection<Guest> findAllWhereNameLike(String firstName, String surname);

    Guest findGuestById(Long id);

    void saveGuest(Guest guest);

    Collection<Guest> findAll();

    Collection<Guest> findAllInHouse();

    Guest lookup(long id);
}
