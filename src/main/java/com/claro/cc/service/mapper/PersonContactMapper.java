package com.claro.cc.service.mapper;

import com.claro.cc.domain.*;
import com.claro.cc.service.dto.PersonContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PersonContact and its DTO PersonContactDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface PersonContactMapper extends EntityMapper<PersonContactDTO, PersonContact> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.fullname", target = "personFullname")
    PersonContactDTO toDto(PersonContact personContact);

    @Mapping(source = "personId", target = "person")
    PersonContact toEntity(PersonContactDTO personContactDTO);

    default PersonContact fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonContact personContact = new PersonContact();
        personContact.setId(id);
        return personContact;
    }
}
