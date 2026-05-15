package com.svalero.equipo5.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse (
        @JsonProperty("access_token")
        String accessToken)
{}
