package com.claro.cc.web.rest;
import com.claro.cc.service.PersonContactService;
import com.claro.cc.web.rest.errors.BadRequestAlertException;
import com.claro.cc.web.rest.util.HeaderUtil;
import com.claro.cc.web.rest.util.PaginationUtil;
import com.claro.cc.service.dto.PersonContactDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PersonContact.
 */
@RestController
@RequestMapping("/api")
public class PersonContactResource {

    private final Logger log = LoggerFactory.getLogger(PersonContactResource.class);

    private static final String ENTITY_NAME = "personContact";

    private final PersonContactService personContactService;

    public PersonContactResource(PersonContactService personContactService) {
        this.personContactService = personContactService;
    }

    /**
     * POST  /person-contacts : Create a new personContact.
     *
     * @param personContactDTO the personContactDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personContactDTO, or with status 400 (Bad Request) if the personContact has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/person-contacts")
    public ResponseEntity<PersonContactDTO> createPersonContact(@Valid @RequestBody PersonContactDTO personContactDTO) throws URISyntaxException {
        log.debug("REST request to save PersonContact : {}", personContactDTO);
        if (personContactDTO.getId() != null) {
            throw new BadRequestAlertException("A new personContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonContactDTO result = personContactService.save(personContactDTO);
        return ResponseEntity.created(new URI("/api/person-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /person-contacts : Updates an existing personContact.
     *
     * @param personContactDTO the personContactDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personContactDTO,
     * or with status 400 (Bad Request) if the personContactDTO is not valid,
     * or with status 500 (Internal Server Error) if the personContactDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/person-contacts")
    public ResponseEntity<PersonContactDTO> updatePersonContact(@Valid @RequestBody PersonContactDTO personContactDTO) throws URISyntaxException {
        log.debug("REST request to update PersonContact : {}", personContactDTO);
        if (personContactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonContactDTO result = personContactService.save(personContactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personContactDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /person-contacts : get all the personContacts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of personContacts in body
     */
    @GetMapping("/person-contacts")
    public ResponseEntity<List<PersonContactDTO>> getAllPersonContacts(Pageable pageable) {
        log.debug("REST request to get a page of PersonContacts");
        Page<PersonContactDTO> page = personContactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/person-contacts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /person-contacts/:id : get the "id" personContact.
     *
     * @param id the id of the personContactDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personContactDTO, or with status 404 (Not Found)
     */
    @GetMapping("/person-contacts/{id}")
    public ResponseEntity<PersonContactDTO> getPersonContact(@PathVariable Long id) {
        log.debug("REST request to get PersonContact : {}", id);
        Optional<PersonContactDTO> personContactDTO = personContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personContactDTO);
    }

    /**
     * DELETE  /person-contacts/:id : delete the "id" personContact.
     *
     * @param id the id of the personContactDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/person-contacts/{id}")
    public ResponseEntity<Void> deletePersonContact(@PathVariable Long id) {
        log.debug("REST request to delete PersonContact : {}", id);
        personContactService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
