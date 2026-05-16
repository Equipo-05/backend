
package com.svalero.equipo5.controller;

import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.dto.out.UserOutDto;
import com.svalero.equipo5.exception.ErrorResponse;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.exception.UserNotFoundException;
import com.svalero.equipo5.service.AuthService;
import com.svalero.equipo5.dto.in.LoginInDto;
import com.svalero.equipo5.dto.in.RegisterInDto;
import com.svalero.equipo5.dto.out.AuthOutDto;
import com.svalero.equipo5.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterInDto registerInDto) {
         authService.register(registerInDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthOutDto> authenticate(@Valid @RequestBody LoginInDto request) throws UserNotFoundException {
        AuthOutDto authOutDto =  authService.login(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(authOutDto);
    }

    @GetMapping("/v1/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> allUser = userService.findAll();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/v2/users")
    public ResponseEntity<List<UserOutDto>> getAllV2() {
        List<UserOutDto> allUser = userService.findAllV2();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) throws UserNotFoundException {
        User user = userService.findUserById(id);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/v2/user/{id}")
    public ResponseEntity<UserOutDto> getUserByIdV2(@PathVariable long id) throws UserNotFoundException {
        UserOutDto user = userService.findUserByIdV2(id);
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/v1/user/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable long id,@Valid @RequestBody RegisterInDto user) throws UserNotFoundException {
        User newUser = userService.modifyUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException gnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The user does not exist");
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





