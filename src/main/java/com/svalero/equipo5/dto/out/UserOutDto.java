package com.svalero.equipo5.dto.out;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.svalero.equipo5.domain.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDto {

    private long id;
    private String email;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private LocalDate birthDate;
    private Float annualSalary;
    private boolean active;
    private String role;

    @JsonBackReference
    private List<Request> grantRequests;

}
