package org.duffy.spring_kafka_chat.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(

    @NotBlank
    String jwt,

    @NotBlank
    String refreshToken
) {}
