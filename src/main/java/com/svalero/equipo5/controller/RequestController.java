package com.svalero.equipo5.controller;

import com.svalero.equipo5.dto.out.RequestOutDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/v1/requests")
    public ResponseEntity<RequestOutDto> createRequest(@RequestBody long grantId ) throws GrantNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.create(grantId));
    }

    @GetMapping("/v1/requests")
    public ResponseEntity<List<RequestOutDto>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAll());
    }

    @GetMapping("/v1/requests/mine")
    public ResponseEntity<List<RequestOutDto>> getMyRequests() {
        return ResponseEntity.ok(requestService.getMyRequests());
    }
}
