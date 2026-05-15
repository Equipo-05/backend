package com.svalero.equipo5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "grants")
public class Grant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column(length = 1000)
    private String description;
    @Column
    private int vacancies;
    @Column
    private boolean available=true;
    @Column(name = "internal_code", unique = true)
    private String internalCode;
    @Column(name = "created_at")
    private LocalDate createdAt=LocalDate.now();

    @OneToMany(mappedBy = "grant",cascade = CascadeType.ALL)
    private List<Request> grantRequests;

}
