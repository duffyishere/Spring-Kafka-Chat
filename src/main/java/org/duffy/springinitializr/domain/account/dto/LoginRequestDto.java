package org.duffy.springinitializr.domain.account.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;

    private String password;
}
