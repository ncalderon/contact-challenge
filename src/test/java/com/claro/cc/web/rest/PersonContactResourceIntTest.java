package com.claro.cc.web.rest;

import com.claro.cc.ContactchallengeApp;

import com.claro.cc.domain.PersonContact;
import com.claro.cc.repository.PersonContactRepository;
import com.claro.cc.service.PersonContactService;
import com.claro.cc.service.dto.PersonContactDTO;
import com.claro.cc.service.mapper.PersonContactMapper;
import com.claro.cc.web.rest.errors.ExceptionTranslator;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.claro.cc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.claro.cc.domain.enumeration.PersonContactType;
/**
 * Test class for the PersonContactResource REST controller.
 *
 * @see PersonContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactchallengeApp.class)
public class PersonContactResourceIntTest {

    private static final PersonContactType DEFAULT_TYPE = PersonContactType.MAIN;
    private static final PersonContactType UPDATED_TYPE = PersonContactType.PHONE;

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private PersonContactRepository personContactRepository;

    @Autowired
    private PersonContactMapper personContactMapper;

    @Autowired
    private PersonContactService personContactService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPersonContactMockMvc;

    private PersonContact personContact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonContactResource personContactResource = new PersonContactResource(personContactService);
        this.restPersonContactMockMvc = MockMvcBuilders.standaloneSetup(personContactResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonContact createEntity(EntityManager em) {
        PersonContact personContact = new PersonContact()
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE);
        return personContact;
    }

    @Before
    public void initTest() {
        personContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonContact() throws Exception {
        int databaseSizeBeforeCreate = personContactRepository.findAll().size();

        // Create the PersonContact
        PersonContactDTO personContactDTO = personContactMapper.toDto(personContact);
        restPersonContactMockMvc.perform(post("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonContact in the database
        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeCreate + 1);
        PersonContact testPersonContact = personContactList.get(personContactList.size() - 1);
        assertThat(testPersonContact.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPersonContact.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createPersonContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personContactRepository.findAll().size();

        // Create the PersonContact with an existing ID
        personContact.setId(1L);
        PersonContactDTO personContactDTO = personContactMapper.toDto(personContact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonContactMockMvc.perform(post("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonContact in the database
        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = personContactRepository.findAll().size();
        // set the field null
        personContact.setType(null);

        // Create the PersonContact, which fails.
        PersonContactDTO personContactDTO = personContactMapper.toDto(personContact);

        restPersonContactMockMvc.perform(post("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isBadRequest());

        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = personContactRepository.findAll().size();
        // set the field null
        personContact.setValue(null);

        // Create the PersonContact, which fails.
        PersonContactDTO personContactDTO = personContactMapper.toDto(personContact);

        restPersonContactMockMvc.perform(post("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isBadRequest());

        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonContacts() throws Exception {
        // Initialize the database
        personContactRepository.saveAndFlush(personContact);

        // Get all the personContactList
        restPersonContactMockMvc.perform(get("/api/person-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getPersonContact() throws Exception {
        // Initialize the database
        personContactRepository.saveAndFlush(personContact);

        // Get the personContact
        restPersonContactMockMvc.perform(get("/api/person-contacts/{id}", personContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personContact.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonContact() throws Exception {
        // Get the personContact
        restPersonContactMockMvc.perform(get("/api/person-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonContact() throws Exception {
        // Initialize the database
        personContactRepository.saveAndFlush(personContact);

        int databaseSizeBeforeUpdate = personContactRepository.findAll().size();

        // Update the personContact
        PersonContact updatedPersonContact = personContactRepository.findById(personContact.getId()).get();
        // Disconnect from session so that the updates on updatedPersonContact are not directly saved in db
        em.detach(updatedPersonContact);
        updatedPersonContact
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE);
        PersonContactDTO personContactDTO = personContactMapper.toDto(updatedPersonContact);

        restPersonContactMockMvc.perform(put("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isOk());

        // Validate the PersonContact in the database
        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeUpdate);
        PersonContact testPersonContact = personContactList.get(personContactList.size() - 1);
        assertThat(testPersonContact.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPersonContact.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonContact() throws Exception {
        int databaseSizeBeforeUpdate = personContactRepository.findAll().size();

        // Create the PersonContact
        PersonContactDTO personContactDTO = personContactMapper.toDto(personContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonContactMockMvc.perform(put("/api/person-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonContact in the database
        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonContact() throws Exception {
        // Initialize the database
        personContactRepository.saveAndFlush(personContact);

        int databaseSizeBeforeDelete = personContactRepository.findAll().size();

        // Delete the personContact
        restPersonContactMockMvc.perform(delete("/api/person-contacts/{id}", personContact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PersonContact> personContactList = personContactRepository.findAll();
        assertThat(personContactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonContact.class);
        PersonContact personContact1 = new PersonContact();
        personContact1.setId(1L);
        PersonContact personContact2 = new PersonContact();
        personContact2.setId(personContact1.getId());
        assertThat(personContact1).isEqualTo(personContact2);
        personContact2.setId(2L);
        assertThat(personContact1).isNotEqualTo(personContact2);
        personContact1.setId(null);
        assertThat(personContact1).isNotEqualTo(personContact2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonContactDTO.class);
        PersonContactDTO personContactDTO1 = new PersonContactDTO();
        personContactDTO1.setId(1L);
        PersonContactDTO personContactDTO2 = new PersonContactDTO();
        assertThat(personContactDTO1).isNotEqualTo(personContactDTO2);
        personContactDTO2.setId(personContactDTO1.getId());
        assertThat(personContactDTO1).isEqualTo(personContactDTO2);
        personContactDTO2.setId(2L);
        assertThat(personContactDTO1).isNotEqualTo(personContactDTO2);
        personContactDTO1.setId(null);
        assertThat(personContactDTO1).isNotEqualTo(personContactDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(personContactMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(personContactMapper.fromId(null)).isNull();
    }
}
