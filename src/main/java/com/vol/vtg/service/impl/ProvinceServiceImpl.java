package com.vol.vtg.service.impl;

import com.vol.vtg.service.ProvinceService;
import com.vol.vtg.domain.Province;
import com.vol.vtg.repository.ProvinceRepository;
import com.vol.vtg.service.dto.ProvinceDTO;
import com.vol.vtg.service.mapper.ProvinceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Province.
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    private final Logger log = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    private final ProvinceRepository provinceRepository;

    private final ProvinceMapper provinceMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    /**
     * Save a province.
     *
     * @param provinceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        log.debug("Request to save Province : {}", provinceDTO);
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        return provinceMapper.toDto(province);
    }

    /**
     * Get all the provinces.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProvinceDTO> findAll() {
        log.debug("Request to get all Provinces");
        return provinceRepository.findAll().stream()
            .map(provinceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one province by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProvinceDTO> findOne(Long id) {
        log.debug("Request to get Province : {}", id);
        return provinceRepository.findById(id)
            .map(provinceMapper::toDto);
    }

    /**
     * Delete the province by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Province : {}", id);
        provinceRepository.deleteById(id);
    }
}
