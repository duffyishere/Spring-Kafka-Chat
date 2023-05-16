package org.duffy.springinitializr.domain.auth;

import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.account.Account;
import org.duffy.springinitializr.domain.account.AccountRepository;
import org.duffy.springinitializr.domain.account.dto.LoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    public LoginResponseDto  refreshToken(String refreshToken) {
        Account account = accountRepository.findByEmail(jwtService.extractUsername(refreshToken)).orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));
        if (jwtService.isTokenValid(refreshToken, account)) {
            String jwt = jwtService.generateToken(account);
            return new LoginResponseDto(jwt, refreshToken);
        }
        else {
            throw new IllegalArgumentException("Expired refresh token.");
        }
    }
}
