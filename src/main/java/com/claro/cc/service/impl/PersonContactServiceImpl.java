package com.claro.cc.service.impl;

import com.claro.cc.service.PersonContactService;
import com.claro.cc.domain.PersonContact;
import com.claro.cc.repository.PersonContactRepository;
import com.claro.cc.service.dto.PersonContactDTO;
import com.claro.cc.service.mapper.PersonContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PersonContact.
 */
@Service
@Transactional
public class PersonContactServiceImpl implements PersonContactService {

    private final Logger log = LoggerFactory.getLogger(PersonContactServiceImpl.class);

    private final PersonContactRepository personContactRepository;

    private final PersonContactMapper personContactMapper;

    public PersonContactServiceImpl(PersonContactRepository personContactRepository, PersonContactMapper personContactMapper) {
        this.personContactRepository = personContactRepository;
        this.personContactMapper = personContactMapper;
    }

    /**
     * Save a personContact.
     *
     * @param personContactDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonContactDTO save(PersonContactDTO personContactDTO) {
        log.debug("Request to save PersonContact : {}", personContactDTO);
        PersonContact personContact = personContactMapper.toEntity(personContactDTO);
        personContact = personContactRepository.save(personContact);
        return personContactMapper.toDto(personContact);
    }

    /**
     * Get all the personContacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PersonContacts");
        return personContactRepository.findAll(pageable)
            .map(personContactMapper::toDto);
    }


    /**
     * Get one personContact by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonContactDTO> findOne(Long id) {
        log.debug("Request to get PersonContact : {}", id);
        return personContactRepository.findById(id)
            .map(personContactMapper::toDto);
    }

    /**
     * Delete the personContact by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonContact : {}", id);
        personContactRepository.deleteById(id);
    }
}
