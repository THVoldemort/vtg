package com.vol.vtg.service;

import com.vol.vtg.service.dto.ProvinceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Province.
 */
public interface ProvinceService {

    /**
     * Save a province.
     *
     * @param provinceDTO the entity to save
     * @return the persisted entity
     */
    ProvinceDTO save(ProvinceDTO provinceDTO);

    /**
     * Get all the provinces.
     *
     * @return the list of entities
     */
    List<ProvinceDTO> findAll();


    /**
     * Get the "id" province.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProvinceDTO> findOne(Long id);

    /**
     * Delete the "id" province.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
