package com.claro.cc.web.rest;
import com.claro.cc.service.PersonInfoService;
import com.claro.cc.service.PersonService;
import com.claro.cc.service.dto.PersonFullDTO;
import com.claro.cc.web.rest.errors.BadRequestAlertException;
import com.claro.cc.web.rest.util.HeaderUtil;
import com.claro.cc.web.rest.util.PaginationUtil;
import com.claro.cc.service.dto.PersonDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Person.
 */
@RestController
@RequestMapping("/api")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private static final String ENTITY_NAME = "person";

    private final PersonService personService;
    private final PersonInfoService personInfoService;

    public PersonResource(PersonService personService, PersonInfoService personInfoService) {
        this.personService = personService;
        this.personInfoService = personInfoService;
    }

    /**
     * POST  /people : Create a new person.
     *
     * @param personFullDTO the personDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personDTO, or with status 400 (Bad Request) if the person has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/people")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonFullDTO personFullDTO) throws URISyntaxException {
        log.debug("REST request to save PersonInfo : {}", personFullDTO);
        if (personFullDTO.getId() != null) {
            throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonDTO result = personInfoService.save(personFullDTO);
        return ResponseEntity.created(new URI("/api/people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /people : Updates an existing person.
     *
     * @param personFullDTO the personFullDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personDTO,
     * or with status 400 (Bad Request) if the personDTO is not valid,
     * or with status 500 (Internal Server Error) if the personDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/people")
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonFullDTO personFullDTO) throws URISyntaxException {
        log.debug("REST request to update PersonInfo : {}", personFullDTO);
        if (personFullDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonDTO result = personInfoService.save(personFullDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personFullDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /people : get all the people.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of people in body
     */
    @GetMapping("/people")
    public ResponseEntity<List<PersonDTO>> getAllPeople(Pageable pageable) {
        log.debug("REST request to get a page of People");
        Page<PersonDTO> page = personService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/people");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /people/:id : get the "id" person.
     *
     * @param id the id of the personDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personDTO, or with status 404 (Not Found)
     */
    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        log.debug("REST request to get Person : {}", id);
        Optional<PersonDTO> personDTO = personService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personDTO);
    }

    /**
     * DELETE  /people/:id : delete the "id" person.
     *
     * @param id the id of the personDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
