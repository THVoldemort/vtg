package com.vol.vtg.web.rest;

import com.vol.vtg.VtgApp;

import com.vol.vtg.domain.Place;
import com.vol.vtg.repository.PlaceRepository;
import com.vol.vtg.service.PlaceService;
import com.vol.vtg.service.dto.PlaceDTO;
import com.vol.vtg.service.mapper.PlaceMapper;
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
 * Test class for the PlaceResource REST controller.
 *
 * @see PlaceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VtgApp.class)
public class PlaceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SLOGAN = "AAAAAAAAAA";
    private static final String UPDATED_SLOGAN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_COMMENT_ID = 1;
    private static final Integer UPDATED_COMMENT_ID = 2;

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

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE_FROM = 1D;
    private static final Double UPDATED_PRICE_FROM = 2D;

    private static final Double DEFAULT_PRICE_TO = 1D;
    private static final Double UPDATED_PRICE_TO = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Integer DEFAULT_PROVINCE_ID = 1;
    private static final Integer UPDATED_PROVINCE_ID = 2;

    @Autowired
    private PlaceRepository placeRepository;


    @Autowired
    private PlaceMapper placeMapper;
    

    @Autowired
    private PlaceService placeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlaceMockMvc;

    private Place place;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlaceResource placeResource = new PlaceResource(placeService);
        this.restPlaceMockMvc = MockMvcBuilders.standaloneSetup(placeResource)
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
    public static Place createEntity(EntityManager em) {
        Place place = new Place()
            .name(DEFAULT_NAME)
            .slogan(DEFAULT_SLOGAN)
            .address(DEFAULT_ADDRESS)
            .rank(DEFAULT_RANK)
            .commentId(DEFAULT_COMMENT_ID)
            .filePath1(DEFAULT_FILE_PATH_1)
            .filePath2(DEFAULT_FILE_PATH_2)
            .filePath3(DEFAULT_FILE_PATH_3)
            .filePath4(DEFAULT_FILE_PATH_4)
            .filePath5(DEFAULT_FILE_PATH_5)
            .introduction(DEFAULT_INTRODUCTION)
            .priceFrom(DEFAULT_PRICE_FROM)
            .priceTo(DEFAULT_PRICE_TO)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .provinceId(DEFAULT_PROVINCE_ID);
        return place;
    }

    @Before
    public void initTest() {
        place = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlace() throws Exception {
        int databaseSizeBeforeCreate = placeRepository.findAll().size();

        // Create the Place
        PlaceDTO placeDTO = placeMapper.toDto(place);
        restPlaceMockMvc.perform(post("/api/places")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(placeDTO)))
            .andExpect(status().isCreated());

        // Validate the Place in the database
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeCreate + 1);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlace.getSlogan()).isEqualTo(DEFAULT_SLOGAN);
        assertThat(testPlace.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPlace.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testPlace.getCommentId()).isEqualTo(DEFAULT_COMMENT_ID);
        assertThat(testPlace.getFilePath1()).isEqualTo(DEFAULT_FILE_PATH_1);
        assertThat(testPlace.getFilePath2()).isEqualTo(DEFAULT_FILE_PATH_2);
        assertThat(testPlace.getFilePath3()).isEqualTo(DEFAULT_FILE_PATH_3);
        assertThat(testPlace.getFilePath4()).isEqualTo(DEFAULT_FILE_PATH_4);
        assertThat(testPlace.getFilePath5()).isEqualTo(DEFAULT_FILE_PATH_5);
        assertThat(testPlace.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testPlace.getPriceFrom()).isEqualTo(DEFAULT_PRICE_FROM);
        assertThat(testPlace.getPriceTo()).isEqualTo(DEFAULT_PRICE_TO);
        assertThat(testPlace.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testPlace.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testPlace.getProvinceId()).isEqualTo(DEFAULT_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void createPlaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = placeRepository.findAll().size();

        // Create the Place with an existing ID
        place.setId(1L);
        PlaceDTO placeDTO = placeMapper.toDto(place);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlaceMockMvc.perform(post("/api/places")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(placeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Place in the database
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlaces() throws Exception {
        // Initialize the database
        placeRepository.saveAndFlush(place);

        // Get all the placeList
        restPlaceMockMvc.perform(get("/api/places?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(place.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].slogan").value(hasItem(DEFAULT_SLOGAN.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].commentId").value(hasItem(DEFAULT_COMMENT_ID)))
            .andExpect(jsonPath("$.[*].filePath1").value(hasItem(DEFAULT_FILE_PATH_1.toString())))
            .andExpect(jsonPath("$.[*].filePath2").value(hasItem(DEFAULT_FILE_PATH_2.toString())))
            .andExpect(jsonPath("$.[*].filePath3").value(hasItem(DEFAULT_FILE_PATH_3.toString())))
            .andExpect(jsonPath("$.[*].filePath4").value(hasItem(DEFAULT_FILE_PATH_4.toString())))
            .andExpect(jsonPath("$.[*].filePath5").value(hasItem(DEFAULT_FILE_PATH_5.toString())))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].priceFrom").value(hasItem(DEFAULT_PRICE_FROM.doubleValue())))
            .andExpect(jsonPath("$.[*].priceTo").value(hasItem(DEFAULT_PRICE_TO.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID)));
    }
    

    @Test
    @Transactional
    public void getPlace() throws Exception {
        // Initialize the database
        placeRepository.saveAndFlush(place);

        // Get the place
        restPlaceMockMvc.perform(get("/api/places/{id}", place.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(place.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.slogan").value(DEFAULT_SLOGAN.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.commentId").value(DEFAULT_COMMENT_ID))
            .andExpect(jsonPath("$.filePath1").value(DEFAULT_FILE_PATH_1.toString()))
            .andExpect(jsonPath("$.filePath2").value(DEFAULT_FILE_PATH_2.toString()))
            .andExpect(jsonPath("$.filePath3").value(DEFAULT_FILE_PATH_3.toString()))
            .andExpect(jsonPath("$.filePath4").value(DEFAULT_FILE_PATH_4.toString()))
            .andExpect(jsonPath("$.filePath5").value(DEFAULT_FILE_PATH_5.toString()))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.priceFrom").value(DEFAULT_PRICE_FROM.doubleValue()))
            .andExpect(jsonPath("$.priceTo").value(DEFAULT_PRICE_TO.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.provinceId").value(DEFAULT_PROVINCE_ID));
    }
    @Test
    @Transactional
    public void getNonExistingPlace() throws Exception {
        // Get the place
        restPlaceMockMvc.perform(get("/api/places/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlace() throws Exception {
        // Initialize the database
        placeRepository.saveAndFlush(place);

        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // Update the place
        Place updatedPlace = placeRepository.findById(place.getId()).get();
        // Disconnect from session so that the updates on updatedPlace are not directly saved in db
        em.detach(updatedPlace);
        updatedPlace
            .name(UPDATED_NAME)
            .slogan(UPDATED_SLOGAN)
            .address(UPDATED_ADDRESS)
            .rank(UPDATED_RANK)
            .commentId(UPDATED_COMMENT_ID)
            .filePath1(UPDATED_FILE_PATH_1)
            .filePath2(UPDATED_FILE_PATH_2)
            .filePath3(UPDATED_FILE_PATH_3)
            .filePath4(UPDATED_FILE_PATH_4)
            .filePath5(UPDATED_FILE_PATH_5)
            .introduction(UPDATED_INTRODUCTION)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .provinceId(UPDATED_PROVINCE_ID);
        PlaceDTO placeDTO = placeMapper.toDto(updatedPlace);

        restPlaceMockMvc.perform(put("/api/places")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(placeDTO)))
            .andExpect(status().isOk());

        // Validate the Place in the database
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlace.getSlogan()).isEqualTo(UPDATED_SLOGAN);
        assertThat(testPlace.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPlace.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testPlace.getCommentId()).isEqualTo(UPDATED_COMMENT_ID);
        assertThat(testPlace.getFilePath1()).isEqualTo(UPDATED_FILE_PATH_1);
        assertThat(testPlace.getFilePath2()).isEqualTo(UPDATED_FILE_PATH_2);
        assertThat(testPlace.getFilePath3()).isEqualTo(UPDATED_FILE_PATH_3);
        assertThat(testPlace.getFilePath4()).isEqualTo(UPDATED_FILE_PATH_4);
        assertThat(testPlace.getFilePath5()).isEqualTo(UPDATED_FILE_PATH_5);
        assertThat(testPlace.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testPlace.getPriceFrom()).isEqualTo(UPDATED_PRICE_FROM);
        assertThat(testPlace.getPriceTo()).isEqualTo(UPDATED_PRICE_TO);
        assertThat(testPlace.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testPlace.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testPlace.getProvinceId()).isEqualTo(UPDATED_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingPlace() throws Exception {
        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // Create the Place
        PlaceDTO placeDTO = placeMapper.toDto(place);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPlaceMockMvc.perform(put("/api/places")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(placeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Place in the database
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlace() throws Exception {
        // Initialize the database
        placeRepository.saveAndFlush(place);

        int databaseSizeBeforeDelete = placeRepository.findAll().size();

        // Get the place
        restPlaceMockMvc.perform(delete("/api/places/{id}", place.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Place.class);
        Place place1 = new Place();
        place1.setId(1L);
        Place place2 = new Place();
        place2.setId(place1.getId());
        assertThat(place1).isEqualTo(place2);
        place2.setId(2L);
        assertThat(place1).isNotEqualTo(place2);
        place1.setId(null);
        assertThat(place1).isNotEqualTo(place2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlaceDTO.class);
        PlaceDTO placeDTO1 = new PlaceDTO();
        placeDTO1.setId(1L);
        PlaceDTO placeDTO2 = new PlaceDTO();
        assertThat(placeDTO1).isNotEqualTo(placeDTO2);
        placeDTO2.setId(placeDTO1.getId());
        assertThat(placeDTO1).isEqualTo(placeDTO2);
        placeDTO2.setId(2L);
        assertThat(placeDTO1).isNotEqualTo(placeDTO2);
        placeDTO1.setId(null);
        assertThat(placeDTO1).isNotEqualTo(placeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(placeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(placeMapper.fromId(null)).isNull();
    }
}
