package com.svalero.equipo5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "grant")
public class Grant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private int vacancies;
    @Column
    private boolean available=true;
    @Column(name = "internal_code")
    private String internalCode;
    @Column(name = "created_at")
    private LocalDate createdAt=LocalDate.now();
}
