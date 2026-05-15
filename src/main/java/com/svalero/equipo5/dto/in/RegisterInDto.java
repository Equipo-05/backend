package com.svalero.equipo5.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class RegisterInDto {
    @NotBlank(message = "DNI is mandatory")
    @Pattern(regexp = "^[0-9]{8}[ABCDEFGHIJKLMNOPQRSTUVWXYZ]$",
            message = "Invalid DNI format")
    private String dni;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be 8+ characters + a/A + number + symbol")
    private String password;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters")
    private String name;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 2, max = 100, message = "Last name must be between 2 to 100 characters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email format not valid")
    private String email;

    @Pattern(regexp = "^(\\+34|0034|34)?[6789]\\d{8}$",
            message = "Phone number not valid")
    private String phone;

    @NotBlank(message = "Role is mandatory ")
    @Pattern(regexp = "^(USER|ADMIN|MANAGER)$",
            message = "Role must be USER, ADMIN o MANAGER")
    private String role;

    @NotNull(message = "Birth date is mandatory")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    private boolean active;

    @PositiveOrZero(message = "Annual salary must be positive")
    private Float annualSalary;
}
