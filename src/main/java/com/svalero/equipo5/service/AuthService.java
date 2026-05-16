package com.svalero.equipo5.service;


import com.svalero.equipo5.domain.Token;
import com.svalero.equipo5.repository.TokenRepository;
import com.svalero.equipo5.dto.in.LoginInDto;
import com.svalero.equipo5.dto.in.RegisterInDto;
import com.svalero.equipo5.dto.out.AuthOutDto;
import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final TokenRepository tokenRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserRepository userRepository;

    public void register(RegisterInDto registerInDto) {
        User user = User.builder()
                .name(registerInDto.getName())
                .lastName(registerInDto.getLastName())
                .password(passwordEncoder.encode(registerInDto.getPassword()))
                .email(registerInDto.getEmail())
                .phone(registerInDto.getPhone())
                .dni(registerInDto.getDni())
                .role(registerInDto.getRole())
                .birthDate(registerInDto.getBirthDate())
                .active(true)
                .annualSalary(registerInDto.getAnnualSalary())
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
    }
    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .type(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthOutDto login(LoginInDto loginInDto)  {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInDto.getDni(),
                        loginInDto.getPassword()
                )
        );
        User user = userRepository.findByDni(loginInDto.getDni());
        if (!user.isActive()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cuenta desactivada. Contacta con el administrador.");
        }
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthOutDto(jwtToken, user.getName(), user.getRole(), user.getEmail(), user.getDni(), user.getId(), user.isActive());
    }

    private void revokeAllUserTokens(User user) {
        final List<Token> validUserTokens = tokenRepository.
                findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
        if (!validUserTokens.isEmpty()) {
            for (Token token : validUserTokens) {
                token.setRevoked(true);
                token.setExpired(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }
    }
}
