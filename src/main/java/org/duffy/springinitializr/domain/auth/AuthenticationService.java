package org.duffy.springinitializr.domain.auth;

import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.account.Account;
import org.duffy.springinitializr.domain.account.AccountRepository;
import org.duffy.springinitializr.domain.auth.dto.LoginRequestDto;
import org.duffy.springinitializr.domain.auth.dto.LoginResponseDto;
import org.duffy.springinitializr.domain.auth.dto.RegisterAccountDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void registerAccount(RegisterAccountDto data) {
        String encryptPassword = passwordEncoder.encode(data.getPassword());
        accountRepository.save(new Account(data, encryptPassword));
    }

    public LoginResponseDto login(LoginRequestDto data) {
        Account account = accountRepository.findByEmail(data.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));

        if (passwordEncoder.matches(data.getPassword(), account.getPassword())) {
            String jwt = jwtService.generateToken(account);
            String refreshToken = jwtService.generateRefreshToken(account);

            return new LoginResponseDto(jwt, refreshToken);
        }
        else
            throw new IllegalArgumentException("Invalid username or password.");
    }

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
