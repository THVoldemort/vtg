package com.vol.vtg.service.impl;

import com.vol.vtg.service.PlaceService;
import com.vol.vtg.domain.Place;
import com.vol.vtg.repository.PlaceRepository;
import com.vol.vtg.service.dto.PlaceDTO;
import com.vol.vtg.service.mapper.PlaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Place.
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    /**
     * Save a place.
     *
     * @param placeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlaceDTO save(PlaceDTO placeDTO) {
        log.debug("Request to save Place : {}", placeDTO);
        Place place = placeMapper.toEntity(placeDTO);
        place = placeRepository.save(place);
        return placeMapper.toDto(place);
    }

    /**
     * Get all the places.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PlaceDTO> findAll() {
        log.debug("Request to get all Places");
        return placeRepository.findAll().stream()
            .map(placeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one place by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlaceDTO> findOne(Long id) {
        log.debug("Request to get Place : {}", id);
        return placeRepository.findById(id)
            .map(placeMapper::toDto);
    }

    /**
     * Delete the place by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Place : {}", id);
        placeRepository.deleteById(id);
    }
}
