package com.vol.vtg.service.mapper;

import com.vol.vtg.domain.*;
import com.vol.vtg.service.dto.HotelRoomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HotelRoom and its DTO HotelRoomDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HotelRoomMapper extends EntityMapper<HotelRoomDTO, HotelRoom> {



    default HotelRoom fromId(Long id) {
        if (id == null) {
            return null;
        }
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setId(id);
        return hotelRoom;
    }
}
