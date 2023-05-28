package org.duffy.springinitializr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
