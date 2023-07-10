package org.duffy.spring_kafka_chat.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterAccountDto(

    @NotBlank
    String email,

    @NotBlank
    String password,

    @NotBlank
    String firstName,

    @NotBlank
    String lastName
) {}
