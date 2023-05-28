package org.duffy.springinitializr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

public record AuthenticationDto(

    @NotBlank
    String jwt,

    @NotBlank
    String refreshToken
) {}
