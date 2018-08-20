package com.vol.vtg.service;

import com.vol.vtg.service.dto.HotelRoomDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing HotelRoom.
 */
public interface HotelRoomService {

    /**
     * Save a hotelRoom.
     *
     * @param hotelRoomDTO the entity to save
     * @return the persisted entity
     */
    HotelRoomDTO save(HotelRoomDTO hotelRoomDTO);

    /**
     * Get all the hotelRooms.
     *
     * @return the list of entities
     */
    List<HotelRoomDTO> findAll();


    /**
     * Get the "id" hotelRoom.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HotelRoomDTO> findOne(Long id);

    /**
     * Delete the "id" hotelRoom.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
