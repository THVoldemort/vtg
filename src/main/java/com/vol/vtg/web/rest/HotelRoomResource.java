package com.vol.vtg.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.vol.vtg.service.HotelRoomService;
import com.vol.vtg.web.rest.errors.BadRequestAlertException;
import com.vol.vtg.web.rest.util.HeaderUtil;
import com.vol.vtg.service.dto.HotelRoomDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing HotelRoom.
 */
@RestController
@RequestMapping("/api")
public class HotelRoomResource {

    private final Logger log = LoggerFactory.getLogger(HotelRoomResource.class);

    private static final String ENTITY_NAME = "hotelRoom";

    private final HotelRoomService hotelRoomService;

    public HotelRoomResource(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }

    /**
     * POST  /hotel-rooms : Create a new hotelRoom.
     *
     * @param hotelRoomDTO the hotelRoomDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hotelRoomDTO, or with status 400 (Bad Request) if the hotelRoom has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hotel-rooms")
    @Timed
    public ResponseEntity<HotelRoomDTO> createHotelRoom(@RequestBody HotelRoomDTO hotelRoomDTO) throws URISyntaxException {
        log.debug("REST request to save HotelRoom : {}", hotelRoomDTO);
        if (hotelRoomDTO.getId() != null) {
            throw new BadRequestAlertException("A new hotelRoom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HotelRoomDTO result = hotelRoomService.save(hotelRoomDTO);
        return ResponseEntity.created(new URI("/api/hotel-rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hotel-rooms : Updates an existing hotelRoom.
     *
     * @param hotelRoomDTO the hotelRoomDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hotelRoomDTO,
     * or with status 400 (Bad Request) if the hotelRoomDTO is not valid,
     * or with status 500 (Internal Server Error) if the hotelRoomDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hotel-rooms")
    @Timed
    public ResponseEntity<HotelRoomDTO> updateHotelRoom(@RequestBody HotelRoomDTO hotelRoomDTO) throws URISyntaxException {
        log.debug("REST request to update HotelRoom : {}", hotelRoomDTO);
        if (hotelRoomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HotelRoomDTO result = hotelRoomService.save(hotelRoomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hotelRoomDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hotel-rooms : get all the hotelRooms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hotelRooms in body
     */
    @GetMapping("/hotel-rooms")
    @Timed
    public List<HotelRoomDTO> getAllHotelRooms() {
        log.debug("REST request to get all HotelRooms");
        return hotelRoomService.findAll();
    }

    /**
     * GET  /hotel-rooms/:id : get the "id" hotelRoom.
     *
     * @param id the id of the hotelRoomDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hotelRoomDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hotel-rooms/{id}")
    @Timed
    public ResponseEntity<HotelRoomDTO> getHotelRoom(@PathVariable Long id) {
        log.debug("REST request to get HotelRoom : {}", id);
        Optional<HotelRoomDTO> hotelRoomDTO = hotelRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hotelRoomDTO);
    }

    /**
     * DELETE  /hotel-rooms/:id : delete the "id" hotelRoom.
     *
     * @param id the id of the hotelRoomDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hotel-rooms/{id}")
    @Timed
    public ResponseEntity<Void> deleteHotelRoom(@PathVariable Long id) {
        log.debug("REST request to delete HotelRoom : {}", id);
        hotelRoomService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
