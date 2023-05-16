package org.duffy.springinitializr.domain.account.dto;

import lombok.Data;

@Data
public class RegisterAccountDto {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
