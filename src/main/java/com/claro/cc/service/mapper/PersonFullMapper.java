package com.claro.cc.service.mapper;

import com.claro.cc.domain.Person;
import com.claro.cc.domain.PersonContact;
import com.claro.cc.service.dto.PersonDTO;
import com.claro.cc.service.dto.PersonFullDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Service
public class PersonFullMapper {

    private AddressMapper addressMapper;
    private PersonContactMapper personContactMapper;

    public PersonFullMapper(AddressMapper addressMapper, PersonContactMapper personContactMapper) {
        this.addressMapper = addressMapper;
        this.personContactMapper = personContactMapper;
    }

    public List<PersonFullDTO> personsToPersonFullDTOs(List<Person> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::personToPersonFullDTO)
            .collect(Collectors.toList());
    }

    public PersonFullDTO personToPersonFullDTO(Person person) {
        PersonFullDTO personFullDTO = new PersonFullDTO(person);
        personFullDTO.setAddresses(person.getAddresses().stream().filter(Objects::nonNull)
            .map(addressMapper::toDto)
           .collect(Collectors.toSet()));

        personFullDTO.setPersonContacts(person.getPersonContacts().stream().filter(Objects::nonNull)
            .map(personContactMapper::toDto)
            .collect(Collectors.toSet()));

        return personFullDTO;
    }

    public List<Person> personFullDTOsToPersons(List<PersonFullDTO> personFullDTOs) {
        return personFullDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::personFullDTOToPerson)
            .collect(Collectors.toList());
    }

    public Person personFullDTOToPerson(PersonFullDTO personFullDTO) {
        if (personFullDTO == null) {
            return null;
        } else {
            Person person = new Person();
            person.setId(personFullDTO.getId());
            person.setBirthday(personFullDTO.getBirthday());
            person.setDocumentNumber(personFullDTO.getDocumentNumber());
            person.setFullname(personFullDTO.getFullname());
            person.setDocumentType(personFullDTO.getDocumentType());
            person.setGender(personFullDTO.getGender());

            person.setAddresses(personFullDTO.getAddresses().stream().filter(Objects::nonNull)
                .map(addressMapper::toEntity)
                .collect(Collectors.toSet()));

            person.setPersonContacts(personFullDTO.getPersonContacts().stream().filter(Objects::nonNull)
                .map(personContactMapper::toEntity)
                .collect(Collectors.toSet()));

            return person;
        }
    }

    public Person personFullFromId(Long id) {
        if (id == null) {
            return null;
        }
        Person Person = new Person();
        Person.setId(id);
        return Person;
    }
}
