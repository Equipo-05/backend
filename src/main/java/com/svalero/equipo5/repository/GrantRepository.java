package com.svalero.equipo5.repository;

import com.svalero.equipo5.domain.Grant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantRepository extends CrudRepository<Grant, Long> {
    List<Grant> findAll();

}
