package com.claro.cc.repository;

import com.claro.cc.domain.PersonContact;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PersonContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact, Long> {

}
