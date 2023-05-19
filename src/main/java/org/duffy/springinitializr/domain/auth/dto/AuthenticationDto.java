package org.duffy.springinitializr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
