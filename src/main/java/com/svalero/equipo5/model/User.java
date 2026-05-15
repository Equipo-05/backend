package com.svalero.equipo5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String dni;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String phone;
    @Column
    private String role;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column
    private boolean active;
    @Column(name = "annual_salary")
    private Float annualSalary;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

//    @OneToMany(mappedBy = "user")
//    private List<GrantRequest> grantRequests;



}
