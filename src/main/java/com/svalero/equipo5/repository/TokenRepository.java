package com.svalero.equipo5.repository;

import com.svalero.equipo5.domain.Token;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TokenRepository extends CrudRepository<Token, Long>, JpaSpecificationExecutor<Token> {
   List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(long userId);

   Token findByToken(String jwtToken);
}