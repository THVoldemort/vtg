package com.vol.vtg.service.impl;

import com.vol.vtg.service.HotelRoomService;
import com.vol.vtg.domain.HotelRoom;
import com.vol.vtg.repository.HotelRoomRepository;
import com.vol.vtg.service.dto.HotelRoomDTO;
import com.vol.vtg.service.mapper.HotelRoomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing HotelRoom.
 */
@Service
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {

    private final Logger log = LoggerFactory.getLogger(HotelRoomServiceImpl.class);

    private final HotelRoomRepository hotelRoomRepository;

    private final HotelRoomMapper hotelRoomMapper;

    public HotelRoomServiceImpl(HotelRoomRepository hotelRoomRepository, HotelRoomMapper hotelRoomMapper) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomMapper = hotelRoomMapper;
    }

    /**
     * Save a hotelRoom.
     *
     * @param hotelRoomDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HotelRoomDTO save(HotelRoomDTO hotelRoomDTO) {
        log.debug("Request to save HotelRoom : {}", hotelRoomDTO);
        HotelRoom hotelRoom = hotelRoomMapper.toEntity(hotelRoomDTO);
        hotelRoom = hotelRoomRepository.save(hotelRoom);
        return hotelRoomMapper.toDto(hotelRoom);
    }

    /**
     * Get all the hotelRooms.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HotelRoomDTO> findAll() {
        log.debug("Request to get all HotelRooms");
        return hotelRoomRepository.findAll().stream()
            .map(hotelRoomMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one hotelRoom by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HotelRoomDTO> findOne(Long id) {
        log.debug("Request to get HotelRoom : {}", id);
        return hotelRoomRepository.findById(id)
            .map(hotelRoomMapper::toDto);
    }

    /**
     * Delete the hotelRoom by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HotelRoom : {}", id);
        hotelRoomRepository.deleteById(id);
    }
}
