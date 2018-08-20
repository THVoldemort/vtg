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

import com.vol.vtg.domain.enumeration.HotelType;
/**
 * Test class for the HotelResource REST controller.
 *
 * @see HotelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VtgApp.class)
public class HotelResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STAR_RANK = 1;
    private static final Integer UPDATED_STAR_RANK = 2;

    private static final String DEFAULT_SLOGAN = "AAAAAAAAAA";
    private static final String UPDATED_SLOGAN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE_FROM = 1D;
    private static final Double UPDATED_PRICE_FROM = 2D;

    private static final Double DEFAULT_PRICE_TO = 1D;
    private static final Double UPDATED_PRICE_TO = 2D;

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Integer DEFAULT_PROVINCE_ID = 1;
    private static final Integer UPDATED_PROVINCE_ID = 2;

    private static final HotelType DEFAULT_HOTEL_TYPE = HotelType.KHACH_SAN;
    private static final HotelType UPDATED_HOTEL_TYPE = HotelType.NHA_NGHI;

    private static final String DEFAULT_FILE_PATH_1 = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH_2 = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH_3 = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH_4 = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH_5 = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH_5 = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLACE_ID = 1;
    private static final Integer UPDATED_PLACE_ID = 2;

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
            .name(DEFAULT_NAME)
            .starRank(DEFAULT_STAR_RANK)
            .slogan(DEFAULT_SLOGAN)
            .address(DEFAULT_ADDRESS)
            .priceFrom(DEFAULT_PRICE_FROM)
            .priceTo(DEFAULT_PRICE_TO)
            .rank(DEFAULT_RANK)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .provinceId(DEFAULT_PROVINCE_ID)
            .hotelType(DEFAULT_HOTEL_TYPE)
            .filePath1(DEFAULT_FILE_PATH_1)
            .filePath2(DEFAULT_FILE_PATH_2)
            .filePath3(DEFAULT_FILE_PATH_3)
            .filePath4(DEFAULT_FILE_PATH_4)
            .filePath5(DEFAULT_FILE_PATH_5)
            .placeId(DEFAULT_PLACE_ID);
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
        assertThat(testHotel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHotel.getStarRank()).isEqualTo(DEFAULT_STAR_RANK);
        assertThat(testHotel.getSlogan()).isEqualTo(DEFAULT_SLOGAN);
        assertThat(testHotel.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testHotel.getPriceFrom()).isEqualTo(DEFAULT_PRICE_FROM);
        assertThat(testHotel.getPriceTo()).isEqualTo(DEFAULT_PRICE_TO);
        assertThat(testHotel.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testHotel.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testHotel.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testHotel.getProvinceId()).isEqualTo(DEFAULT_PROVINCE_ID);
        assertThat(testHotel.getHotelType()).isEqualTo(DEFAULT_HOTEL_TYPE);
        assertThat(testHotel.getFilePath1()).isEqualTo(DEFAULT_FILE_PATH_1);
        assertThat(testHotel.getFilePath2()).isEqualTo(DEFAULT_FILE_PATH_2);
        assertThat(testHotel.getFilePath3()).isEqualTo(DEFAULT_FILE_PATH_3);
        assertThat(testHotel.getFilePath4()).isEqualTo(DEFAULT_FILE_PATH_4);
        assertThat(testHotel.getFilePath5()).isEqualTo(DEFAULT_FILE_PATH_5);
        assertThat(testHotel.getPlaceId()).isEqualTo(DEFAULT_PLACE_ID);
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
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].starRank").value(hasItem(DEFAULT_STAR_RANK)))
            .andExpect(jsonPath("$.[*].slogan").value(hasItem(DEFAULT_SLOGAN.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].priceFrom").value(hasItem(DEFAULT_PRICE_FROM.doubleValue())))
            .andExpect(jsonPath("$.[*].priceTo").value(hasItem(DEFAULT_PRICE_TO.doubleValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID)))
            .andExpect(jsonPath("$.[*].hotelType").value(hasItem(DEFAULT_HOTEL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].filePath1").value(hasItem(DEFAULT_FILE_PATH_1.toString())))
            .andExpect(jsonPath("$.[*].filePath2").value(hasItem(DEFAULT_FILE_PATH_2.toString())))
            .andExpect(jsonPath("$.[*].filePath3").value(hasItem(DEFAULT_FILE_PATH_3.toString())))
            .andExpect(jsonPath("$.[*].filePath4").value(hasItem(DEFAULT_FILE_PATH_4.toString())))
            .andExpect(jsonPath("$.[*].filePath5").value(hasItem(DEFAULT_FILE_PATH_5.toString())))
            .andExpect(jsonPath("$.[*].placeId").value(hasItem(DEFAULT_PLACE_ID)));
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
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.starRank").value(DEFAULT_STAR_RANK))
            .andExpect(jsonPath("$.slogan").value(DEFAULT_SLOGAN.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.priceFrom").value(DEFAULT_PRICE_FROM.doubleValue()))
            .andExpect(jsonPath("$.priceTo").value(DEFAULT_PRICE_TO.doubleValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.provinceId").value(DEFAULT_PROVINCE_ID))
            .andExpect(jsonPath("$.hotelType").value(DEFAULT_HOTEL_TYPE.toString()))
            .andExpect(jsonPath("$.filePath1").value(DEFAULT_FILE_PATH_1.toString()))
            .andExpect(jsonPath("$.filePath2").value(DEFAULT_FILE_PATH_2.toString()))
            .andExpect(jsonPath("$.filePath3").value(DEFAULT_FILE_PATH_3.toString()))
            .andExpect(jsonPath("$.filePath4").value(DEFAULT_FILE_PATH_4.toString()))
            .andExpect(jsonPath("$.filePath5").value(DEFAULT_FILE_PATH_5.toString()))
            .andExpect(jsonPath("$.placeId").value(DEFAULT_PLACE_ID));
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
            .name(UPDATED_NAME)
            .starRank(UPDATED_STAR_RANK)
            .slogan(UPDATED_SLOGAN)
            .address(UPDATED_ADDRESS)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .rank(UPDATED_RANK)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .provinceId(UPDATED_PROVINCE_ID)
            .hotelType(UPDATED_HOTEL_TYPE)
            .filePath1(UPDATED_FILE_PATH_1)
            .filePath2(UPDATED_FILE_PATH_2)
            .filePath3(UPDATED_FILE_PATH_3)
            .filePath4(UPDATED_FILE_PATH_4)
            .filePath5(UPDATED_FILE_PATH_5)
            .placeId(UPDATED_PLACE_ID);
        HotelDTO hotelDTO = hotelMapper.toDto(updatedHotel);

        restHotelMockMvc.perform(put("/api/hotels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
            .andExpect(status().isOk());

        // Validate the Hotel in the database
        List<Hotel> hotelList = hotelRepository.findAll();
        assertThat(hotelList).hasSize(databaseSizeBeforeUpdate);
        Hotel testHotel = hotelList.get(hotelList.size() - 1);
        assertThat(testHotel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHotel.getStarRank()).isEqualTo(UPDATED_STAR_RANK);
        assertThat(testHotel.getSlogan()).isEqualTo(UPDATED_SLOGAN);
        assertThat(testHotel.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testHotel.getPriceFrom()).isEqualTo(UPDATED_PRICE_FROM);
        assertThat(testHotel.getPriceTo()).isEqualTo(UPDATED_PRICE_TO);
        assertThat(testHotel.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testHotel.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testHotel.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testHotel.getProvinceId()).isEqualTo(UPDATED_PROVINCE_ID);
        assertThat(testHotel.getHotelType()).isEqualTo(UPDATED_HOTEL_TYPE);
        assertThat(testHotel.getFilePath1()).isEqualTo(UPDATED_FILE_PATH_1);
        assertThat(testHotel.getFilePath2()).isEqualTo(UPDATED_FILE_PATH_2);
        assertThat(testHotel.getFilePath3()).isEqualTo(UPDATED_FILE_PATH_3);
        assertThat(testHotel.getFilePath4()).isEqualTo(UPDATED_FILE_PATH_4);
        assertThat(testHotel.getFilePath5()).isEqualTo(UPDATED_FILE_PATH_5);
        assertThat(testHotel.getPlaceId()).isEqualTo(UPDATED_PLACE_ID);
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
