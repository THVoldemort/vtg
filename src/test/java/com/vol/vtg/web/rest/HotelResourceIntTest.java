package com.vol.vtg.web.rest;

import com.vol.vtg.VtgApp;

import com.vol.vtg.domain.Hotel;
import com.vol.vtg.repository.HotelRepository;
import com.vol.vtg.service.HotelService;
import com.vol.vtg.service.dto.HotelDTO;
import com.vol.vtg.service.mapper.HotelMapper;
import com.vol.vtg.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.vol.vtg.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HotelResource REST controller.
 *
 * @see HotelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VtgApp.class)
public class HotelResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_OF_ROOM = 1;
    private static final Integer UPDATED_NUM_OF_ROOM = 2;

    private static final Double DEFAULT_LATITDE = 1D;
    private static final Double UPDATED_LATITDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    @Autowired
    private HotelRepository hotelRepository;


    @Autowired
    private HotelMapper hotelMapper;
    

    @Autowired
    private HotelService hotelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHotelMockMvc;

    private Hotel hotel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HotelResource hotelResource = new HotelResource(hotelService);
        this.restHotelMockMvc = MockMvcBuilders.standaloneSetup(hotelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hotel createEntity(EntityManager em) {
        Hotel hotel = new Hotel()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .numOfRoom(DEFAULT_NUM_OF_ROOM)
            .latitde(DEFAULT_LATITDE)
            .longitude(DEFAULT_LONGITUDE);
        return hotel;
    }

    @Before
    public void initTest() {
        hotel = createEntity(em);
    }

    @Test
    @Transactional
    public void createHotel() throws Exception {
        int databaseSizeBeforeCreate = hotelRepository.findAll().size();

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);
        restHotelMockMvc.perform(post("/api/hotels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isCreated());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeCreate + 1);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testHotel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHotel.getNumOfRoom()).isEqualTo(DEFAULT_NUM_OF_ROOM);
        assertThat(testHotel.getLatitde()).isEqualTo(DEFAULT_LATITDE);
        assertThat(testHotel.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createHotelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hotelRepository.findAll().size();

        // Create the Hotel with an existing ID
        hotel.setId(1L);
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHotelMockMvc.perform(post("/api/hotels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHotels() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get all the hotelList
        restHotelMockMvc.perform(get("/api/hotels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hotel.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].numOfRoom").value(hasItem(DEFAULT_NUM_OF_ROOM)))
            .andExpect(jsonPath("$.[*].latitde").value(hasItem(DEFAULT_LATITDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        // Get the hotel
        restHotelMockMvc.perform(get("/api/hotels/{id}", hotel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hotel.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.numOfRoom").value(DEFAULT_NUM_OF_ROOM))
            .andExpect(jsonPath("$.latitde").value(DEFAULT_LATITDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingHotel() throws Exception {
        // Get the hotel
        restHotelMockMvc.perform(get("/api/hotels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();

        // Update the hotel
        Hotel updatedHotel = hotelRepository.findById(hotel.getId()).get();
        // Disconnect from session so that the updates on updatedHotel are not directly saved in db
        em.detach(updatedHotel);
        updatedHotel
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .numOfRoom(UPDATED_NUM_OF_ROOM)
            .latitde(UPDATED_LATITDE)
            .longitude(UPDATED_LONGITUDE);
        HotelDTO hotelDTO = hotelMapper.toDto(updatedHotel);

        restHotelMockMvc.perform(put("/api/hotels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isOk());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testHotel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHotel.getNumOfRoom()).isEqualTo(UPDATED_NUM_OF_ROOM);
        assertThat(testHotel.getLatitde()).isEqualTo(UPDATED_LATITDE);
        assertThat(testHotel.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingHotel() throws Exception {
        int databaseSizeBeforeUpdate = hotelRepository.findAll().size();

        // Create the Hotel
        HotelDTO hotelDTO = hotelMapper.toDto(hotel);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHotelMockMvc.perform(put("/api/hotels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHotel() throws Exception {
        // Initialize the database
        hotelRepository.saveAndFlush(hotel);

        int databaseSizeBeforeDelete = hotelRepository.findAll().size();

        // Get the hotel
        restHotelMockMvc.perform(delete("/api/hotels/{id}", hotel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hotel.class);
        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        Hotel hotel2 = new Hotel();
        hotel2.setId(hotel1.getId());
        assertThat(hotel1).isEqualTo(hotel2);
        hotel2.setId(2L);
        assertThat(hotel1).isNotEqualTo(hotel2);
        hotel1.setId(null);
        assertThat(hotel1).isNotEqualTo(hotel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HotelDTO.class);
        HotelDTO hotelDTO1 = new HotelDTO();
        hotelDTO1.setId(1L);
        HotelDTO hotelDTO2 = new HotelDTO();
        assertThat(hotelDTO1).isNotEqualTo(hotelDTO2);
        hotelDTO2.setId(hotelDTO1.getId());
        assertThat(hotelDTO1).isEqualTo(hotelDTO2);
        hotelDTO2.setId(2L);
        assertThat(hotelDTO1).isNotEqualTo(hotelDTO2);
        hotelDTO1.setId(null);
        assertThat(hotelDTO1).isNotEqualTo(hotelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hotelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hotelMapper.fromId(null)).isNull();
    }
}
