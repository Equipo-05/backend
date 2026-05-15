package com.svalero.equipo5.auth.service;

import com.svalero.equipo5.auth.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final TokenRepository tokenRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
}
