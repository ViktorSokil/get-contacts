package com.sokil.repository;

import com.sokil.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name="org.hibernate.cacheRegion",value="contact")})
    List<Contact> findAll();
}
