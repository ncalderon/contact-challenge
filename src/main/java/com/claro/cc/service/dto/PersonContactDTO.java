package com.claro.cc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.claro.cc.domain.enumeration.PersonContactType;

/**
 * A DTO for the PersonContact entity.
 */
public class PersonContactDTO implements Serializable {

    private Long id;

    @NotNull
    private PersonContactType type;

    @NotNull
    private String value;


    private Long personId;

    private String personFullname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonContactType getType() {
        return type;
    }

    public void setType(PersonContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFullname() {
        return personFullname;
    }

    public void setPersonFullname(String personFullname) {
        this.personFullname = personFullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonContactDTO personContactDTO = (PersonContactDTO) o;
        if (personContactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personContactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonContactDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", value='" + getValue() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFullname() + "'" +
            "}";
    }
}
