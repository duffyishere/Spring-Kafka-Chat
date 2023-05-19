package org.duffy.springinitializr.domain.auth;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.auth.dto.LoginRequestDto;
import org.duffy.springinitializr.domain.auth.dto.RegisterAccountDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register account")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterAccountDto body) {
        authenticationService.registerAccount(body);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get JWT by email and pwd")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body) {
        return ResponseEntity.ok(authenticationService.login(body));
    }

    @Operation(summary = "Get new JWT by the Refresh token")
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
