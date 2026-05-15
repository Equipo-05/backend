package com.svalero.equipo5.auth.repository;

import com.svalero.equipo5.auth.model.Token;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;



public interface TokenRepository extends CrudRepository<Token, Long>, JpaSpecificationExecutor<Token> {

}