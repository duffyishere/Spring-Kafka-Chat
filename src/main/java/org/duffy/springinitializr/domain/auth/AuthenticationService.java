package org.duffy.springinitializr.domain.auth;

import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.account.Account;
import org.duffy.springinitializr.domain.account.AccountRepository;
import org.duffy.springinitializr.domain.auth.dto.LoginRequestDto;
import org.duffy.springinitializr.domain.auth.dto.AuthenticationDto;
import org.duffy.springinitializr.domain.auth.dto.RegisterAccountDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void registerAccount(RegisterAccountDto data) {
        String encryptPassword = passwordEncoder.encode(data.password());
        accountRepository.save(new Account(data, encryptPassword));
    }

    @Transactional(readOnly = true)
    public AuthenticationDto login(LoginRequestDto data) {
        Account account = accountRepository.findByEmail(data.email()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        if (passwordEncoder.matches(data.password(), account.getPassword())) {
            String jwt = jwtService.generateToken(account);
            String refreshToken = jwtService.generateRefreshToken(account);

            return new AuthenticationDto(jwt, refreshToken);
        }
        else
            throw new IllegalArgumentException("Invalid email or password.");
    }

    @Transactional(readOnly = true)
    public AuthenticationDto refreshToken(String refreshToken) {
        Account account = accountRepository.findByEmail(jwtService.extractUsername(refreshToken)).orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));
        if (jwtService.isTokenValid(refreshToken, account)) {
            String jwt = jwtService.generateToken(account);
            return new AuthenticationDto(jwt, refreshToken);
        }
        else {
            throw new IllegalArgumentException("Expired refresh token.");
        }
    }
}
