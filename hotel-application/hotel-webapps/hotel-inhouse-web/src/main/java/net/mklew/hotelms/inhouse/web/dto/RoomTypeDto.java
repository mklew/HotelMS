package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.domain.room.RoomType;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/25/12
 *        time 7:06 PM
 */
@XmlRootElement(name = "roomTypeDto")
public class RoomTypeDto
{
    public String id;

    public RoomTypeDto()
    {

    }

    public RoomTypeDto(String id)
    {
        this.id = id;
    }

    public static Collection<RoomTypeDto> create(Collection<RoomType> roomTypes)
    {
        Collection<RoomTypeDto> roomTypeDtos = new ArrayList<>(roomTypes.size());
        for(RoomType roomType : roomTypes)
        {
            roomTypeDtos.add(new RoomTypeDto(roomType.getTypeName()));
        }
        return roomTypeDtos;
    }
}
