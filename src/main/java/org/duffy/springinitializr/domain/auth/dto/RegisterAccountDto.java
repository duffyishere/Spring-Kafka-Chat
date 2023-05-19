package org.duffy.springinitializr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterAccountDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
