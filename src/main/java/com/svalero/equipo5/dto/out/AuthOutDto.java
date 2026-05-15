package com.svalero.equipo5.dto.out;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthOutDto {
    private String token;
    private String name;
    private String role;
    private String email;
    private String dni;
    private long userId;
    private boolean active;
}

