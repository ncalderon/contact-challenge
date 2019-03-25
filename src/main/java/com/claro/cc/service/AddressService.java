package com.claro.cc.service;

import com.claro.cc.service.dto.AddressDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    AddressDTO save(AddressDTO addressDTO);

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AddressDTO> findAll(Pageable pageable);


    /**
     * Get the "id" address.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AddressDTO> findOne(Long id);

    /**
     * Delete the "id" address.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
