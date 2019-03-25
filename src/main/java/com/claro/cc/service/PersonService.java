package com.claro.cc.service;

import com.claro.cc.service.dto.PersonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Person.
 */
public interface PersonService {

    /**
     * Save a person.
     *
     * @param personDTO the entity to save
     * @return the persisted entity
     */
    PersonDTO save(PersonDTO personDTO);

    /**
     * Get all the people.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PersonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" person.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PersonDTO> findOne(Long id);

    /**
     * Delete the "id" person.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
