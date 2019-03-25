package com.claro.cc.service;

import com.claro.cc.service.dto.PersonContactDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PersonContact.
 */
public interface PersonContactService {

    /**
     * Save a personContact.
     *
     * @param personContactDTO the entity to save
     * @return the persisted entity
     */
    PersonContactDTO save(PersonContactDTO personContactDTO);

    /**
     * Get all the personContacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PersonContactDTO> findAll(Pageable pageable);


    /**
     * Get the "id" personContact.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PersonContactDTO> findOne(Long id);

    /**
     * Delete the "id" personContact.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
