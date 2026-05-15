
package com.svalero.equipo5.controller;

import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.service.AuthService;
import com.svalero.equipo5.dto.in.LoginInDto;
import com.svalero.equipo5.dto.in.RegisterInDto;
import com.svalero.equipo5.dto.out.AuthOutDto;
import com.svalero.equipo5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterInDto registerInDto) {
        TokenResponse token = authService.register(registerInDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthOutDto> authenticate(@RequestBody LoginInDto request) throws UserNotFoundException {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/v1/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> allUser = userService.findAll();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) throws UserNotFoundException {
        User user = userService.findUserById(id);
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/v1/user/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable long id, @RequestBody User user) throws UserNotFoundException {
        User newUser = userService.modifyUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}





