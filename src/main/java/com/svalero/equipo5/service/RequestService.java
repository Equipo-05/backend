package com.svalero.equipo5.service;

import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.domain.Request;
import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.dto.out.RequestOutDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.repository.GrantRepository;
import com.svalero.equipo5.repository.RequestRepository;
import com.svalero.equipo5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private GrantRepository grantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    public RequestOutDto create(long grantId) throws GrantNotFoundException {
        User user = getAuthenticatedUser();
        Grant grant = grantRepository.findById(grantId).orElseThrow(GrantNotFoundException::new);

        Request request = new Request();
        request.setUser(user);
        request.setGrant(grant);

        Request savedRequest = requestRepository.save(request);
        return toDto(savedRequest);
    }

    public RequestOutDto toDto(Request req) {
        RequestOutDto dto = new RequestOutDto();
        dto.setId(req.getId());
        dto.setUserId(req.getUser().getId());
        dto.setGrantId(req.getGrant().getId());
        dto.setUserName(req.getUser().getName());
        dto.setGrantName(req.getGrant().getName());
        dto.setStatus(req.getStatus());
        dto.setCreatedAt(req.getCreatedAt());
        dto.setResolvedAt(req.getResolvedAt());
        return dto;
    }

    private User getAuthenticatedUser() {
        String dni = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByDni(dni);
    }

    public List<RequestOutDto> getMyRequests() {
        User user = getAuthenticatedUser();
        return requestRepository.findByUserId(user.getId())
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<RequestOutDto> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserId(userId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<RequestOutDto> getAll() {
        return requestRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public void delete(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public RequestOutDto updateStatus(long requestId, Request.Status newStatus) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(newStatus);
        if (newStatus != Request.Status.PENDING) {
            request.setResolvedAt(LocalDateTime.now());
        }

        Request savedRequest = requestRepository.save(request);
        return toDto(savedRequest);
    }
}