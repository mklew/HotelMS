package net.mklew.hotelms.domain.room;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/11/12
 *        Time: 5:56 PM
 */
public interface RoomRepository
{
    Room getRoomByName(RoomName name);
}
