package com.sokil.repository;

import com.sokil.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //name = HINT_FETCH_SIZE, value = ""+Integer.MIN_VALUE this means that data will process row by row from db
    //and GC will clear variables faster. For MySQL value should = ""+Integer.MIN_VALUE
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "1"))
    @Query(value = "select t from Contact t")
    Stream<Contact> streamAll();

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "1"))
    @Query("select c from Contact c where c.id between :minId and :maxId")
    Stream<Contact> findAllByBetweenId(@Param("minId") Integer minId, @Param("maxId") Integer maxId);

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "1"))
    @Query(value = "select t from Contact t")
    Stream<Contact> streamAllPaged(Pageable pageable);
}
