package com.example.smart_money_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
    @JsonProperty("token")
    String token
) {}
