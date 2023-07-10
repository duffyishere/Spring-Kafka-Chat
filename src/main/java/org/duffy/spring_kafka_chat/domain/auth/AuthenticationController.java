package org.duffy.spring_kafka_chat.domain.auth;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.duffy.spring_kafka_chat.domain.auth.dto.LoginRequestDto;
import org.duffy.spring_kafka_chat.domain.auth.dto.RegisterAccountDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
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

    @Operation(summary = "Get updated JWT by the Refresh token")
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
