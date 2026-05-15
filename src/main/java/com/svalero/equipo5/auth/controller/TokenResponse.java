package com.svalero.equipo5.auth.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse (
        @JsonProperty("access_token")
        String accessToken)
{}
