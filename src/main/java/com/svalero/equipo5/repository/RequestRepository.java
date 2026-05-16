package com.svalero.equipo5.repository;

import com.svalero.equipo5.domain.Request;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long>, JpaSpecificationExecutor<Request> {
    List<Request> findByUserId(long userId);
    List<Request> findAll();
}
