package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.room.Room;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/3/12
 *        Time: 9:10 PM
 */
public interface RateRepository
{
    public Collection<Rate> getAllRatesForRoom(Room room);
}
