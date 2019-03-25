package com.claro.cc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.claro.cc.domain.enumeration.AddressType;

/**
 * A DTO for the Address entity.
 */
public class AddressDTO implements Serializable {

    private Long id;

    @NotNull
    private AddressType type;

    @NotNull
    @Size(min = 4, max = 64)
    private String country;

    @NotNull
    @Size(min = 4, max = 64)
    private String state;

    @NotNull
    @Size(min = 4, max = 64)
    private String city;

    @NotNull
    @Size(min = 4, max = 64)
    private String neighborhood;

    @NotNull
    @Size(min = 4, max = 64)
    private String street;

    @NotNull
    private String fullAddress;


    private Long personId;

    private String personFullname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
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

        AddressDTO addressDTO = (AddressDTO) o;
        if (addressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", country='" + getCountry() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", street='" + getStreet() + "'" +
            ", fullAddress='" + getFullAddress() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFullname() + "'" +
            "}";
    }
}
