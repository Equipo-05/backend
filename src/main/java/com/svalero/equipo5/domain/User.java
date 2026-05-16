package com.svalero.equipo5.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String dni;
    @Column
    private String password;
    @Column(unique = true)
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return dni;
    }

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonBackReference
   private List<Request> grantRequests;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Token> tokens;


}
