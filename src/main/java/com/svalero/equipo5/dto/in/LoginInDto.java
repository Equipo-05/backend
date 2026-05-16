package com.svalero.equipo5.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginInDto {

    @NotBlank(message = "DNI is mandatory")
    @Pattern(regexp = "^[0-9]{8}[ABCDEFGHIJKLMNOPQRSTUVWXYZ]$", message = "Invalid DNI format")
    private String dni;

    @NotBlank(message = "Password is mandatory")
    private String password;
}