package com.svalero.equipo5.dto.out;

import com.svalero.equipo5.domain.Request;
import com.svalero.equipo5.repository.RequestRepository;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestOutDto {
    private long id;
    private long userId;
    private String userName;
    private long grantId;
    private String grantName;
    private Request.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
