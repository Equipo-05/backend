package com.svalero.equipo5.dto.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantModifyInDto {
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Type is mandatory")
    private String type;
    @NotNull(message = "Description is mandatory")
    private String description;
    @Min(value = 1, message = "The number of vacancies must be at least 1")
    private int vacancies;
    private String internalCode;
    private LocalDate createdAt;
    private boolean available;

}
