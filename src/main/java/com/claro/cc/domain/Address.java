package com.claro.cc.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.claro.cc.domain.enumeration.AddressType;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private AddressType type;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "country", length = 64, nullable = false)
    private String country;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "state", length = 64, nullable = false)
    private String state;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "city", length = 64, nullable = false)
    private String city;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "neighborhood", length = 64, nullable = false)
    private String neighborhood;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "street", length = 64, nullable = false)
    private String street;

    @NotNull
    @Column(name = "full_address", nullable = false)
    private String fullAddress;

    @ManyToOne
    @JsonIgnoreProperties("addresses")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressType getType() {
        return type;
    }

    public Address type(AddressType type) {
        this.type = type;
        return this;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public Address state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Address neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public Address fullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Person getPerson() {
        return person;
    }

    public Address person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        if (address.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", country='" + getCountry() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", street='" + getStreet() + "'" +
            ", fullAddress='" + getFullAddress() + "'" +
            "}";
    }
}
