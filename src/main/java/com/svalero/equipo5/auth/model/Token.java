package com.svalero.equipo5.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tokens")
public class Token {

    public enum TokenType{
        BEARER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType =  TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

}
