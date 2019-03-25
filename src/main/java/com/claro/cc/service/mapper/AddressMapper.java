package com.claro.cc.service.mapper;

import com.claro.cc.domain.*;
import com.claro.cc.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.fullname", target = "personFullname")
    AddressDTO toDto(Address address);

    @Mapping(source = "personId", target = "person")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
