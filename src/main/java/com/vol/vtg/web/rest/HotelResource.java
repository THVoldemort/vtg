package com.vol.vtg.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.vol.vtg.service.HotelService;
import com.vol.vtg.service.dto.HotelDTO;
import com.vol.vtg.web.rest.errors.BadRequestAlertException;
import com.vol.vtg.web.rest.util.HeaderUtil;
import com.vol.vtg.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Hotel.
 */
@RestController
@RequestMapping("/api")
public class HotelResource {

	private final Logger log = LoggerFactory.getLogger(HotelResource.class);

	private static final String ENTITY_NAME = "hotel";

	private final HotelService hotelService;

	public HotelResource(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	/**
	 * POST /hotels : Create a new hotel.
	 *
	 * @param hotelDTO
	 *            the hotelDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         hotelDTO, or with status 400 (Bad Request) if the hotel has already
	 *         an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/hotels")
	@Timed
	public Result<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) throws URISyntaxException {
		log.debug("REST request to save Hotel : {}", hotelDTO);
		if (hotelDTO.getId() != null) {
			throw new BadRequestAlertException("A new hotel cannot already have an ID", ENTITY_NAME, "idexists");
		}
		HotelDTO result = hotelService.save(hotelDTO);
		return Result.createSuccess(result);
	}

	/**
	 * PUT /hotels : Updates an existing hotel.
	 *
	 * @param hotelDTO
	 *            the hotelDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         hotelDTO, or with status 400 (Bad Request) if the hotelDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the hotelDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/hotels")
	@Timed
	public Result<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) throws URISyntaxException {
		log.debug("REST request to update Hotel : {}", hotelDTO);
		if (hotelDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		HotelDTO result = hotelService.save(hotelDTO);
		return Result.createSuccess(result);
	}

	/**
	 * GET /hotels : get all the hotels.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of hotels in
	 *         body
	 */
	@GetMapping("/hotels")
	@Timed
	public Result<Page<HotelDTO>> getAllHotels(Pageable pageable) {
		log.debug("REST request to get a page of Hotels");
		Page<HotelDTO> page = hotelService.findAll(pageable);
		return Result.createSuccess(page);
	}

	/**
	 * GET /hotels/:id : get the "id" hotel.
	 *
	 * @param id
	 *            the id of the hotelDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the hotelDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/hotels/{id}")
	@Timed
	public Result<HotelDTO> getHotel(@PathVariable Long id) {
		log.debug("REST request to get Hotel : {}", id);
		Optional<HotelDTO> hotelDTO = hotelService.findOne(id);
		return Result.createFromOptional(hotelDTO);
	}

	/**
	 * DELETE /hotels/:id : delete the "id" hotel.
	 *
	 * @param id
	 *            the id of the hotelDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/hotels/{id}")
	@Timed
	public Result<Void> deleteHotel(@PathVariable Long id) {
		log.debug("REST request to delete Hotel : {}", id);
		hotelService.delete(id);
		return Result.createSuccess(null);
	}

}
