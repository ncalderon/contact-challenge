package com.claro.cc.service.mapper;

import com.claro.cc.domain.*;
import com.claro.cc.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    PersonDTO toDto(Person person);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "personContacts", ignore = true)
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
