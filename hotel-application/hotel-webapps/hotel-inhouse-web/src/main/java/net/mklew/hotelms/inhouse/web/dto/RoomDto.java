package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.domain.room.Room;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/25/12
 *        time 7:29 PM
 */
@XmlRootElement(name = "roomdto")
public class RoomDto
{

    public String id;
    public String number;
    public String roomType;
    public Integer maxExtraBeds;

    public RoomDto()
    {
    }

    public RoomDto(String id, String number, String roomType, Integer maxExtraBeds)
    {
        this.id = id;
        this.number = number;
        this.roomType = roomType;
        this.maxExtraBeds = maxExtraBeds;
    }

    public static Collection<RoomDto> create(Collection<Room> rooms)
    {
        Collection<RoomDto> roomDtos = new ArrayList<>(rooms.size());
        for(Room room : rooms)
        {
            RoomDto roomDto = new RoomDto();
            roomDto.id = room.fullRoomName();
            roomDto.number = room.roomNumber();
            roomDto.roomType = room.roomTypeName();
            roomDto.maxExtraBeds = room.maxExtraBeds();

            roomDtos.add(roomDto);
        }
        return roomDtos;
    }
}
