package net.mklew.hotelms.domain.room;

import org.joda.time.DateTime;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/11/12
 *        Time: 5:56 PM
 */
public interface RoomRepository
{
    Room getRoomByName(RoomName name) throws RoomNotFoundException;

    Collection<RoomType> getAllRoomTypes();

    Collection<Room> getAllRooms();

    boolean isRoomAvailableBetweenDates(Room room, DateTime checkIn, DateTime checkOut);
}
