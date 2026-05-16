package com.svalero.equipo5.controller;

import com.svalero.equipo5.domain.Request;
import com.svalero.equipo5.dto.out.RequestOutDto;
import com.svalero.equipo5.exception.ErrorResponse;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.exception.RequestNotFoundException;
import com.svalero.equipo5.exception.UserNotFoundException;
import com.svalero.equipo5.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/v1/requests/{id}/status")
    public ResponseEntity<RequestOutDto> updateStatus(@PathVariable long id, @RequestParam Request.Status status) throws RequestNotFoundException {
        return ResponseEntity.ok(requestService.updateStatus(id, status));
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(RequestNotFoundException rnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The request does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
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
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException bce) {
        ErrorResponse errorResponse = ErrorResponse.generalError(
                401,
                "bad-credentials",
                "DNI or Password not correct"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
