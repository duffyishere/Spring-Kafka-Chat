package org.duffy.springinitializr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginRequestDto(

    @NotBlank
    String email,

    @NotBlank
    String password
) {}
