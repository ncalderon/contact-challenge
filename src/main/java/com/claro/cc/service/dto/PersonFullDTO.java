package com.claro.cc.service.dto;

import com.claro.cc.domain.Person;
import com.claro.cc.domain.enumeration.Gender;
import com.claro.cc.domain.enumeration.PersonDocumentType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Person entity.
 */
public class PersonFullDTO extends PersonDTO implements Serializable {

    public PersonFullDTO(Person person) {
        super(person);
    }

    private UserDTO user;

    private Set<AddressDTO> addresses;

    private Set<PersonContactDTO> personContacts;

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public Set<PersonContactDTO> getPersonContacts() {
        return personContacts;
    }

    public void setPersonContacts(Set<PersonContactDTO> personContacts) {
        this.personContacts = personContacts;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonFullDTO personDTO = (PersonFullDTO) o;
        if (personDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonFullDTO{" +
            "id=" + getId() +
            ", fullname='" + getFullname() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", gender='" + getGender() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            "}";
    }
}
