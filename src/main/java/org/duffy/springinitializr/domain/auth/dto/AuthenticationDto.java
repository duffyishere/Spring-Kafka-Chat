package org.duffy.springinitializr.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {

    private String token;

    private String refreshToken;
}
