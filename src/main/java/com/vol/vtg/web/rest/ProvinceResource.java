package com.vol.vtg.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.vol.vtg.common.Result;
import com.vol.vtg.service.ProvinceService;
import com.vol.vtg.service.dto.ProvinceDTO;
import com.vol.vtg.web.rest.errors.BadRequestAlertException;
import com.vol.vtg.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Province.
 */
@RestController
@RequestMapping("/api")
public class ProvinceResource {

    private final Logger log = LoggerFactory.getLogger(ProvinceResource.class);

    private static final String ENTITY_NAME = "province";

    private final ProvinceService provinceService;

    public ProvinceResource(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    /**
     * POST  /provinces : Create a new province.
     *
     * @param provinceDTO the provinceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provinceDTO, or with status 400 (Bad Request) if the province has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provinces")
    @Timed
    public Result<ProvinceDTO> createProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to save Province : {}", provinceDTO);
        if (provinceDTO.getId() != null) {
            throw new BadRequestAlertException("A new province cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvinceDTO result = provinceService.save(provinceDTO);
        return Result.createSuccess(result);
    }

    /**
     * PUT  /provinces : Updates an existing province.
     *
     * @param provinceDTO the provinceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provinceDTO,
     * or with status 400 (Bad Request) if the provinceDTO is not valid,
     * or with status 500 (Internal Server Error) if the provinceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provinces")
    @Timed
    public ResponseEntity<ProvinceDTO> updateProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to update Province : {}", provinceDTO);
        if (provinceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvinceDTO result = provinceService.save(provinceDTO);
        return Result.createSuccess(result);
    }

    /**
     * GET  /provinces : get all the provinces.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of provinces in body
     */
    @GetMapping("/provinces")
    @Timed
    public Result<List<ProvinceDTO>> getAllProvinces() {
        log.debug("REST request to get all Provinces");
        return Result.createSuccess(provinceService.findAll()) ;
    }

    /**
     * GET  /provinces/:id : get the "id" province.
     *
     * @param id the id of the provinceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provinceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/provinces/{id}")
    @Timed
    public Result<ProvinceDTO> getProvince(@PathVariable Long id) {
        log.debug("REST request to get Province : {}", id);
        Optional<ProvinceDTO> provinceDTO = provinceService.findOne(id);
        return Result.createFromOptional(provinceDTO);
    }

    /**
     * DELETE  /provinces/:id : delete the "id" province.
     *
     * @param id the id of the provinceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provinces/{id}")
    @Timed
    public Result<Void> deleteProvince(@PathVariable Long id) {
        log.debug("REST request to delete Province : {}", id);
        provinceService.delete(id);
        return Result.createSuccess(null);
    }
}
