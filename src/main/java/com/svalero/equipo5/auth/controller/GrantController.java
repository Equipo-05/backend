package com.svalero.equipo5.auth.controller;

import com.svalero.equipo5.auth.repository.GrantRepository;
import com.svalero.equipo5.auth.service.GrantService;
import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.dto.in.GrantInDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrantController {

    @Autowired
    private GrantService grantService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/grants")
    public ResponseEntity<List<Grant>> getAll() {
        List<Grant> allGrants = grantService.findAll();
        return ResponseEntity.ok(allGrants);
    }

    @GetMapping("/grants/{id}")
    public ResponseEntity<Grant> getGrantById(@PathVariable long id) throws GrantNotFoundException {
        Grant grant = grantService.findGrantById(id);
        return  ResponseEntity.ok(grant);
    }

    @PostMapping("/grants")
    public ResponseEntity<Grant> addGrant(@RequestBody GrantInDto grantInDto) {
        Grant newGrant = grantService.addGrant(grantInDto);
        return new ResponseEntity<>(newGrant, HttpStatus.CREATED);
    }

    @PutMapping("/grants")
    public ResponseEntity<Grant> modifyGrant(@PathVariable long id, @RequestBody Grant grant) throws GrantNotFoundException {
        Grant newGrant = grantService.modifyGrant(id, grant);
        return ResponseEntity.ok(newGrant);
    }

    @DeleteMapping("/grants")
    public ResponseEntity<Void> deleteGrant(@PathVariable long id) throws GrantNotFoundException {
        grantService.deleteGrant(id);
        return ResponseEntity.noContent().build();
    }
}
