package com.svalero.equipo5.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "grant_id")
    private Grant grant;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.PENDING;


    @Column(name ="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;


    public enum Status{
        PENDING,
        APPROVED,
        REJECTED
    }
}
