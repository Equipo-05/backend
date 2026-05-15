
package com.svalero.equipo5.controller;

import com.svalero.equipo5.service.AuthService;
import com.svalero.equipo5.dto.in.LoginInDto;
import com.svalero.equipo5.dto.in.RegisterInDto;
import com.svalero.equipo5.dto.out.AuthOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterInDto registerInDto) throws Exception {
        TokenResponse token = authService.register(registerInDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthOutDto> authenticate(@RequestBody LoginInDto request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }



}

