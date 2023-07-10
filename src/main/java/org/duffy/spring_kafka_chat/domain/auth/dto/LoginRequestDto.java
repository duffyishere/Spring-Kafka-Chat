package org.duffy.spring_kafka_chat.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(

    @NotBlank
    String email,

    @NotBlank
    String password
) {}
