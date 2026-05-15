package com.svalero.equipo5.dto.in;

import lombok.Data;

import java.time.LocalDate;

@Data

public class RegisterInDto {

    private String dni;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private LocalDate birthDate;
    private boolean active;
    private Float annualSalary;
}
