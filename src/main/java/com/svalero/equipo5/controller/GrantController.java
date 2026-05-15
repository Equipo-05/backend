package com.svalero.equipo5.controller;

import com.svalero.equipo5.dto.in.GrantModifyInDto;
import com.svalero.equipo5.exception.ErrorResponse;
import com.svalero.equipo5.service.GrantService;
import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.dto.in.GrantInDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GrantController {

    @Autowired
    private GrantService grantService;


    @GetMapping("/v1/grants")
    public ResponseEntity<List<Grant>> getAllGrants() {
        List<Grant> allGrants = grantService.findAll();
        return ResponseEntity.ok(allGrants);
    }

    @GetMapping("/v1/grants/{id}")
    public ResponseEntity<Grant> getGrantById(@PathVariable long id) throws GrantNotFoundException {
        Grant grant = grantService.findGrantById(id);
        return  ResponseEntity.ok(grant);
    }

    @PostMapping("/v1/grants")
    public ResponseEntity<Grant> addGrant(@Valid @RequestBody GrantInDto grantInDto) {
        Grant newGrant = grantService.addGrant(grantInDto);
        return new ResponseEntity<>(newGrant, HttpStatus.CREATED);
    }

    @PutMapping("/v1/grants/{id}")
    public ResponseEntity<Grant> modifyGrant(@PathVariable long id, @RequestBody GrantModifyInDto grantModifyInDto) throws GrantNotFoundException {
        Grant newGrant = grantService.modifyGrant(id, grantModifyInDto);
        return ResponseEntity.ok(newGrant);
    }

    @DeleteMapping("/v1/grants/{id}")
    public ResponseEntity<Void> deleteGrant(@PathVariable long id) throws GrantNotFoundException {
        grantService.deleteGrant(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v2/grants/{id}")
    public ResponseEntity<Void> deleteGrantV2(@PathVariable long id) throws GrantNotFoundException {
        grantService.deleteGrantV2(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(GrantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(GrantNotFoundException gnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The grant does not exist");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        //extraemos los errores de la excepción del fallo
        manve.getBindingResult().getAllErrors().forEach(error -> { //para cada error rellenamos el nombre del campo
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message); //asociamos cada error con su mensaje
        });
        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.internalServerError();
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
