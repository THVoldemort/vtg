package com.vol.vtg.web.rest;

import com.vol.vtg.VtgApp;

import com.vol.vtg.domain.HotelRoom;
import com.vol.vtg.repository.HotelRoomRepository;
import com.vol.vtg.service.HotelRoomService;
import com.vol.vtg.service.dto.HotelRoomDTO;
import com.vol.vtg.service.mapper.HotelRoomMapper;
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
 * Test class for the HotelRoomResource REST controller.
 *
 * @see HotelRoomResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VtgApp.class)
public class HotelRoomResourceIntTest {

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONVENIENT = 1;
    private static final Integer UPDATED_CONVENIENT = 2;

    private static final Integer DEFAULT_CANCEL_FEE_DAY_1 = 1;
    private static final Integer UPDATED_CANCEL_FEE_DAY_1 = 2;

    private static final Integer DEFAULT_CANCEL_FEE_DAY_2 = 1;
    private static final Integer UPDATED_CANCEL_FEE_DAY_2 = 2;

    private static final String DEFAULT_CANCEL_FEE_TIME_1 = "AAAAAAAAAA";
    private static final String UPDATED_CANCEL_FEE_TIME_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CANCEL_FEE_TIME_2 = "AAAAAAAAAA";
    private static final String UPDATED_CANCEL_FEE_TIME_2 = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING_ID = 1;
    private static final Integer UPDATED_RATING_ID = 2;

    private static final Integer DEFAULT_NUM_OF_ADULT = 1;
    private static final Integer UPDATED_NUM_OF_ADULT = 2;

    private static final Integer DEFAULT_NUM_OF_CHILD = 1;
    private static final Integer UPDATED_NUM_OF_CHILD = 2;

    private static final Double DEFAULT_PRICE_EST = 1D;
    private static final Double UPDATED_PRICE_EST = 2D;

    private static final Double DEFAULT_PRICE_ACT = 1D;
    private static final Double UPDATED_PRICE_ACT = 2D;

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

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

    @Autowired
    private HotelRoomRepository hotelRoomRepository;


    @Autowired
    private HotelRoomMapper hotelRoomMapper;
    

    @Autowired
    private HotelRoomService hotelRoomService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHotelRoomMockMvc;

    private HotelRoom hotelRoom;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HotelRoomResource hotelRoomResource = new HotelRoomResource(hotelRoomService);
        this.restHotelRoomMockMvc = MockMvcBuilders.standaloneSetup(hotelRoomResource)
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
    public static HotelRoom createEntity(EntityManager em) {
        HotelRoom hotelRoom = new HotelRoom()
            .type(DEFAULT_TYPE)
            .introduction(DEFAULT_INTRODUCTION)
            .convenient(DEFAULT_CONVENIENT)
            .cancelFeeDay1(DEFAULT_CANCEL_FEE_DAY_1)
            .cancelFeeDay2(DEFAULT_CANCEL_FEE_DAY_2)
            .cancelFeeTime1(DEFAULT_CANCEL_FEE_TIME_1)
            .cancelFeeTime2(DEFAULT_CANCEL_FEE_TIME_2)
            .ratingId(DEFAULT_RATING_ID)
            .numOfAdult(DEFAULT_NUM_OF_ADULT)
            .numOfChild(DEFAULT_NUM_OF_CHILD)
            .priceEst(DEFAULT_PRICE_EST)
            .priceAct(DEFAULT_PRICE_ACT)
            .quantity(DEFAULT_QUANTITY)
            .filePath1(DEFAULT_FILE_PATH_1)
            .filePath2(DEFAULT_FILE_PATH_2)
            .filePath3(DEFAULT_FILE_PATH_3)
            .filePath4(DEFAULT_FILE_PATH_4)
            .filePath5(DEFAULT_FILE_PATH_5);
        return hotelRoom;
    }

    @Before
    public void initTest() {
        hotelRoom = createEntity(em);
    }

    @Test
    @Transactional
    public void createHotelRoom() throws Exception {
        int databaseSizeBeforeCreate = hotelRoomRepository.findAll().size();

        // Create the HotelRoom
        HotelRoomDTO hotelRoomDTO = hotelRoomMapper.toDto(hotelRoom);
        restHotelRoomMockMvc.perform(post("/api/hotel-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelRoomDTO)))
            .andExpect(status().isCreated());

        // Validate the HotelRoom in the database
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();
        assertThat(hotelRoomList).hasSize(databaseSizeBeforeCreate + 1);
        HotelRoom testHotelRoom = hotelRoomList.get(hotelRoomList.size() - 1);
        assertThat(testHotelRoom.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testHotelRoom.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testHotelRoom.getConvenient()).isEqualTo(DEFAULT_CONVENIENT);
        assertThat(testHotelRoom.getCancelFeeDay1()).isEqualTo(DEFAULT_CANCEL_FEE_DAY_1);
        assertThat(testHotelRoom.getCancelFeeDay2()).isEqualTo(DEFAULT_CANCEL_FEE_DAY_2);
        assertThat(testHotelRoom.getCancelFeeTime1()).isEqualTo(DEFAULT_CANCEL_FEE_TIME_1);
        assertThat(testHotelRoom.getCancelFeeTime2()).isEqualTo(DEFAULT_CANCEL_FEE_TIME_2);
        assertThat(testHotelRoom.getRatingId()).isEqualTo(DEFAULT_RATING_ID);
        assertThat(testHotelRoom.getNumOfAdult()).isEqualTo(DEFAULT_NUM_OF_ADULT);
        assertThat(testHotelRoom.getNumOfChild()).isEqualTo(DEFAULT_NUM_OF_CHILD);
        assertThat(testHotelRoom.getPriceEst()).isEqualTo(DEFAULT_PRICE_EST);
        assertThat(testHotelRoom.getPriceAct()).isEqualTo(DEFAULT_PRICE_ACT);
        assertThat(testHotelRoom.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testHotelRoom.getFilePath1()).isEqualTo(DEFAULT_FILE_PATH_1);
        assertThat(testHotelRoom.getFilePath2()).isEqualTo(DEFAULT_FILE_PATH_2);
        assertThat(testHotelRoom.getFilePath3()).isEqualTo(DEFAULT_FILE_PATH_3);
        assertThat(testHotelRoom.getFilePath4()).isEqualTo(DEFAULT_FILE_PATH_4);
        assertThat(testHotelRoom.getFilePath5()).isEqualTo(DEFAULT_FILE_PATH_5);
    }

    @Test
    @Transactional
    public void createHotelRoomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hotelRoomRepository.findAll().size();

        // Create the HotelRoom with an existing ID
        hotelRoom.setId(1L);
        HotelRoomDTO hotelRoomDTO = hotelRoomMapper.toDto(hotelRoom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHotelRoomMockMvc.perform(post("/api/hotel-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelRoomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HotelRoom in the database
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();
        assertThat(hotelRoomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHotelRooms() throws Exception {
        // Initialize the database
        hotelRoomRepository.saveAndFlush(hotelRoom);

        // Get all the hotelRoomList
        restHotelRoomMockMvc.perform(get("/api/hotel-rooms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hotelRoom.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].convenient").value(hasItem(DEFAULT_CONVENIENT)))
            .andExpect(jsonPath("$.[*].cancelFeeDay1").value(hasItem(DEFAULT_CANCEL_FEE_DAY_1)))
            .andExpect(jsonPath("$.[*].cancelFeeDay2").value(hasItem(DEFAULT_CANCEL_FEE_DAY_2)))
            .andExpect(jsonPath("$.[*].cancelFeeTime1").value(hasItem(DEFAULT_CANCEL_FEE_TIME_1.toString())))
            .andExpect(jsonPath("$.[*].cancelFeeTime2").value(hasItem(DEFAULT_CANCEL_FEE_TIME_2.toString())))
            .andExpect(jsonPath("$.[*].ratingId").value(hasItem(DEFAULT_RATING_ID)))
            .andExpect(jsonPath("$.[*].numOfAdult").value(hasItem(DEFAULT_NUM_OF_ADULT)))
            .andExpect(jsonPath("$.[*].numOfChild").value(hasItem(DEFAULT_NUM_OF_CHILD)))
            .andExpect(jsonPath("$.[*].priceEst").value(hasItem(DEFAULT_PRICE_EST.doubleValue())))
            .andExpect(jsonPath("$.[*].priceAct").value(hasItem(DEFAULT_PRICE_ACT.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].filePath1").value(hasItem(DEFAULT_FILE_PATH_1.toString())))
            .andExpect(jsonPath("$.[*].filePath2").value(hasItem(DEFAULT_FILE_PATH_2.toString())))
            .andExpect(jsonPath("$.[*].filePath3").value(hasItem(DEFAULT_FILE_PATH_3.toString())))
            .andExpect(jsonPath("$.[*].filePath4").value(hasItem(DEFAULT_FILE_PATH_4.toString())))
            .andExpect(jsonPath("$.[*].filePath5").value(hasItem(DEFAULT_FILE_PATH_5.toString())));
    }
    

    @Test
    @Transactional
    public void getHotelRoom() throws Exception {
        // Initialize the database
        hotelRoomRepository.saveAndFlush(hotelRoom);

        // Get the hotelRoom
        restHotelRoomMockMvc.perform(get("/api/hotel-rooms/{id}", hotelRoom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hotelRoom.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.convenient").value(DEFAULT_CONVENIENT))
            .andExpect(jsonPath("$.cancelFeeDay1").value(DEFAULT_CANCEL_FEE_DAY_1))
            .andExpect(jsonPath("$.cancelFeeDay2").value(DEFAULT_CANCEL_FEE_DAY_2))
            .andExpect(jsonPath("$.cancelFeeTime1").value(DEFAULT_CANCEL_FEE_TIME_1.toString()))
            .andExpect(jsonPath("$.cancelFeeTime2").value(DEFAULT_CANCEL_FEE_TIME_2.toString()))
            .andExpect(jsonPath("$.ratingId").value(DEFAULT_RATING_ID))
            .andExpect(jsonPath("$.numOfAdult").value(DEFAULT_NUM_OF_ADULT))
            .andExpect(jsonPath("$.numOfChild").value(DEFAULT_NUM_OF_CHILD))
            .andExpect(jsonPath("$.priceEst").value(DEFAULT_PRICE_EST.doubleValue()))
            .andExpect(jsonPath("$.priceAct").value(DEFAULT_PRICE_ACT.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.filePath1").value(DEFAULT_FILE_PATH_1.toString()))
            .andExpect(jsonPath("$.filePath2").value(DEFAULT_FILE_PATH_2.toString()))
            .andExpect(jsonPath("$.filePath3").value(DEFAULT_FILE_PATH_3.toString()))
            .andExpect(jsonPath("$.filePath4").value(DEFAULT_FILE_PATH_4.toString()))
            .andExpect(jsonPath("$.filePath5").value(DEFAULT_FILE_PATH_5.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingHotelRoom() throws Exception {
        // Get the hotelRoom
        restHotelRoomMockMvc.perform(get("/api/hotel-rooms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHotelRoom() throws Exception {
        // Initialize the database
        hotelRoomRepository.saveAndFlush(hotelRoom);

        int databaseSizeBeforeUpdate = hotelRoomRepository.findAll().size();

        // Update the hotelRoom
        HotelRoom updatedHotelRoom = hotelRoomRepository.findById(hotelRoom.getId()).get();
        // Disconnect from session so that the updates on updatedHotelRoom are not directly saved in db
        em.detach(updatedHotelRoom);
        updatedHotelRoom
            .type(UPDATED_TYPE)
            .introduction(UPDATED_INTRODUCTION)
            .convenient(UPDATED_CONVENIENT)
            .cancelFeeDay1(UPDATED_CANCEL_FEE_DAY_1)
            .cancelFeeDay2(UPDATED_CANCEL_FEE_DAY_2)
            .cancelFeeTime1(UPDATED_CANCEL_FEE_TIME_1)
            .cancelFeeTime2(UPDATED_CANCEL_FEE_TIME_2)
            .ratingId(UPDATED_RATING_ID)
            .numOfAdult(UPDATED_NUM_OF_ADULT)
            .numOfChild(UPDATED_NUM_OF_CHILD)
            .priceEst(UPDATED_PRICE_EST)
            .priceAct(UPDATED_PRICE_ACT)
            .quantity(UPDATED_QUANTITY)
            .filePath1(UPDATED_FILE_PATH_1)
            .filePath2(UPDATED_FILE_PATH_2)
            .filePath3(UPDATED_FILE_PATH_3)
            .filePath4(UPDATED_FILE_PATH_4)
            .filePath5(UPDATED_FILE_PATH_5);
        HotelRoomDTO hotelRoomDTO = hotelRoomMapper.toDto(updatedHotelRoom);

        restHotelRoomMockMvc.perform(put("/api/hotel-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelRoomDTO)))
            .andExpect(status().isOk());

        // Validate the HotelRoom in the database
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();
        assertThat(hotelRoomList).hasSize(databaseSizeBeforeUpdate);
        HotelRoom testHotelRoom = hotelRoomList.get(hotelRoomList.size() - 1);
        assertThat(testHotelRoom.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testHotelRoom.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testHotelRoom.getConvenient()).isEqualTo(UPDATED_CONVENIENT);
        assertThat(testHotelRoom.getCancelFeeDay1()).isEqualTo(UPDATED_CANCEL_FEE_DAY_1);
        assertThat(testHotelRoom.getCancelFeeDay2()).isEqualTo(UPDATED_CANCEL_FEE_DAY_2);
        assertThat(testHotelRoom.getCancelFeeTime1()).isEqualTo(UPDATED_CANCEL_FEE_TIME_1);
        assertThat(testHotelRoom.getCancelFeeTime2()).isEqualTo(UPDATED_CANCEL_FEE_TIME_2);
        assertThat(testHotelRoom.getRatingId()).isEqualTo(UPDATED_RATING_ID);
        assertThat(testHotelRoom.getNumOfAdult()).isEqualTo(UPDATED_NUM_OF_ADULT);
        assertThat(testHotelRoom.getNumOfChild()).isEqualTo(UPDATED_NUM_OF_CHILD);
        assertThat(testHotelRoom.getPriceEst()).isEqualTo(UPDATED_PRICE_EST);
        assertThat(testHotelRoom.getPriceAct()).isEqualTo(UPDATED_PRICE_ACT);
        assertThat(testHotelRoom.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testHotelRoom.getFilePath1()).isEqualTo(UPDATED_FILE_PATH_1);
        assertThat(testHotelRoom.getFilePath2()).isEqualTo(UPDATED_FILE_PATH_2);
        assertThat(testHotelRoom.getFilePath3()).isEqualTo(UPDATED_FILE_PATH_3);
        assertThat(testHotelRoom.getFilePath4()).isEqualTo(UPDATED_FILE_PATH_4);
        assertThat(testHotelRoom.getFilePath5()).isEqualTo(UPDATED_FILE_PATH_5);
    }

    @Test
    @Transactional
    public void updateNonExistingHotelRoom() throws Exception {
        int databaseSizeBeforeUpdate = hotelRoomRepository.findAll().size();

        // Create the HotelRoom
        HotelRoomDTO hotelRoomDTO = hotelRoomMapper.toDto(hotelRoom);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHotelRoomMockMvc.perform(put("/api/hotel-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hotelRoomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HotelRoom in the database
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();
        assertThat(hotelRoomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHotelRoom() throws Exception {
        // Initialize the database
        hotelRoomRepository.saveAndFlush(hotelRoom);

        int databaseSizeBeforeDelete = hotelRoomRepository.findAll().size();

        // Get the hotelRoom
        restHotelRoomMockMvc.perform(delete("/api/hotel-rooms/{id}", hotelRoom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();
        assertThat(hotelRoomList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HotelRoom.class);
        HotelRoom hotelRoom1 = new HotelRoom();
        hotelRoom1.setId(1L);
        HotelRoom hotelRoom2 = new HotelRoom();
        hotelRoom2.setId(hotelRoom1.getId());
        assertThat(hotelRoom1).isEqualTo(hotelRoom2);
        hotelRoom2.setId(2L);
        assertThat(hotelRoom1).isNotEqualTo(hotelRoom2);
        hotelRoom1.setId(null);
        assertThat(hotelRoom1).isNotEqualTo(hotelRoom2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HotelRoomDTO.class);
        HotelRoomDTO hotelRoomDTO1 = new HotelRoomDTO();
        hotelRoomDTO1.setId(1L);
        HotelRoomDTO hotelRoomDTO2 = new HotelRoomDTO();
        assertThat(hotelRoomDTO1).isNotEqualTo(hotelRoomDTO2);
        hotelRoomDTO2.setId(hotelRoomDTO1.getId());
        assertThat(hotelRoomDTO1).isEqualTo(hotelRoomDTO2);
        hotelRoomDTO2.setId(2L);
        assertThat(hotelRoomDTO1).isNotEqualTo(hotelRoomDTO2);
        hotelRoomDTO1.setId(null);
        assertThat(hotelRoomDTO1).isNotEqualTo(hotelRoomDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hotelRoomMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hotelRoomMapper.fromId(null)).isNull();
    }
}
