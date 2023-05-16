package org.duffy.springinitializr.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String jwt;

    private String refreshToken;
}
