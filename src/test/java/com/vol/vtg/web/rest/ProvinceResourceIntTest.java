package com.vol.vtg.web.rest;

import com.vol.vtg.VtgApp;

import com.vol.vtg.domain.Province;
import com.vol.vtg.repository.ProvinceRepository;
import com.vol.vtg.service.ProvinceService;
import com.vol.vtg.service.dto.ProvinceDTO;
import com.vol.vtg.service.mapper.ProvinceMapper;
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
 * Test class for the ProvinceResource REST controller.
 *
 * @see ProvinceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VtgApp.class)
public class ProvinceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SLOGAN = "AAAAAAAAAA";
    private static final String UPDATED_SLOGAN = "BBBBBBBBBB";

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_RATING_ID = 1;
    private static final Integer UPDATED_RATING_ID = 2;

    @Autowired
    private ProvinceRepository provinceRepository;


    @Autowired
    private ProvinceMapper provinceMapper;
    

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProvinceMockMvc;

    private Province province;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvinceResource provinceResource = new ProvinceResource(provinceService);
        this.restProvinceMockMvc = MockMvcBuilders.standaloneSetup(provinceResource)
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
    public static Province createEntity(EntityManager em) {
        Province province = new Province()
            .name(DEFAULT_NAME)
            .slogan(DEFAULT_SLOGAN)
            .introduction(DEFAULT_INTRODUCTION)
            .filePath1(DEFAULT_FILE_PATH_1)
            .filePath2(DEFAULT_FILE_PATH_2)
            .filePath3(DEFAULT_FILE_PATH_3)
            .filePath4(DEFAULT_FILE_PATH_4)
            .filePath5(DEFAULT_FILE_PATH_5)
            .ratingId(DEFAULT_RATING_ID);
        return province;
    }

    @Before
    public void initTest() {
        province = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvince() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isCreated());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate + 1);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProvince.getSlogan()).isEqualTo(DEFAULT_SLOGAN);
        assertThat(testProvince.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testProvince.getFilePath1()).isEqualTo(DEFAULT_FILE_PATH_1);
        assertThat(testProvince.getFilePath2()).isEqualTo(DEFAULT_FILE_PATH_2);
        assertThat(testProvince.getFilePath3()).isEqualTo(DEFAULT_FILE_PATH_3);
        assertThat(testProvince.getFilePath4()).isEqualTo(DEFAULT_FILE_PATH_4);
        assertThat(testProvince.getFilePath5()).isEqualTo(DEFAULT_FILE_PATH_5);
        assertThat(testProvince.getRatingId()).isEqualTo(DEFAULT_RATING_ID);
    }

    @Test
    @Transactional
    public void createProvinceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province with an existing ID
        province.setId(1L);
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProvinces() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        // Get all the provinceList
        restProvinceMockMvc.perform(get("/api/provinces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(province.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].slogan").value(hasItem(DEFAULT_SLOGAN.toString())))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].filePath1").value(hasItem(DEFAULT_FILE_PATH_1.toString())))
            .andExpect(jsonPath("$.[*].filePath2").value(hasItem(DEFAULT_FILE_PATH_2.toString())))
            .andExpect(jsonPath("$.[*].filePath3").value(hasItem(DEFAULT_FILE_PATH_3.toString())))
            .andExpect(jsonPath("$.[*].filePath4").value(hasItem(DEFAULT_FILE_PATH_4.toString())))
            .andExpect(jsonPath("$.[*].filePath5").value(hasItem(DEFAULT_FILE_PATH_5.toString())))
            .andExpect(jsonPath("$.[*].ratingId").value(hasItem(DEFAULT_RATING_ID)));
    }
    

    @Test
    @Transactional
    public void getProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", province.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(province.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.slogan").value(DEFAULT_SLOGAN.toString()))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.filePath1").value(DEFAULT_FILE_PATH_1.toString()))
            .andExpect(jsonPath("$.filePath2").value(DEFAULT_FILE_PATH_2.toString()))
            .andExpect(jsonPath("$.filePath3").value(DEFAULT_FILE_PATH_3.toString()))
            .andExpect(jsonPath("$.filePath4").value(DEFAULT_FILE_PATH_4.toString()))
            .andExpect(jsonPath("$.filePath5").value(DEFAULT_FILE_PATH_5.toString()))
            .andExpect(jsonPath("$.ratingId").value(DEFAULT_RATING_ID));
    }
    @Test
    @Transactional
    public void getNonExistingProvince() throws Exception {
        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Update the province
        Province updatedProvince = provinceRepository.findById(province.getId()).get();
        // Disconnect from session so that the updates on updatedProvince are not directly saved in db
        em.detach(updatedProvince);
        updatedProvince
            .name(UPDATED_NAME)
            .slogan(UPDATED_SLOGAN)
            .introduction(UPDATED_INTRODUCTION)
            .filePath1(UPDATED_FILE_PATH_1)
            .filePath2(UPDATED_FILE_PATH_2)
            .filePath3(UPDATED_FILE_PATH_3)
            .filePath4(UPDATED_FILE_PATH_4)
            .filePath5(UPDATED_FILE_PATH_5)
            .ratingId(UPDATED_RATING_ID);
        ProvinceDTO provinceDTO = provinceMapper.toDto(updatedProvince);

        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isOk());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProvince.getSlogan()).isEqualTo(UPDATED_SLOGAN);
        assertThat(testProvince.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testProvince.getFilePath1()).isEqualTo(UPDATED_FILE_PATH_1);
        assertThat(testProvince.getFilePath2()).isEqualTo(UPDATED_FILE_PATH_2);
        assertThat(testProvince.getFilePath3()).isEqualTo(UPDATED_FILE_PATH_3);
        assertThat(testProvince.getFilePath4()).isEqualTo(UPDATED_FILE_PATH_4);
        assertThat(testProvince.getFilePath5()).isEqualTo(UPDATED_FILE_PATH_5);
        assertThat(testProvince.getRatingId()).isEqualTo(UPDATED_RATING_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProvince() throws Exception {
        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        int databaseSizeBeforeDelete = provinceRepository.findAll().size();

        // Get the province
        restProvinceMockMvc.perform(delete("/api/provinces/{id}", province.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Province.class);
        Province province1 = new Province();
        province1.setId(1L);
        Province province2 = new Province();
        province2.setId(province1.getId());
        assertThat(province1).isEqualTo(province2);
        province2.setId(2L);
        assertThat(province1).isNotEqualTo(province2);
        province1.setId(null);
        assertThat(province1).isNotEqualTo(province2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinceDTO.class);
        ProvinceDTO provinceDTO1 = new ProvinceDTO();
        provinceDTO1.setId(1L);
        ProvinceDTO provinceDTO2 = new ProvinceDTO();
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO2.setId(provinceDTO1.getId());
        assertThat(provinceDTO1).isEqualTo(provinceDTO2);
        provinceDTO2.setId(2L);
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO1.setId(null);
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(provinceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(provinceMapper.fromId(null)).isNull();
    }
}
