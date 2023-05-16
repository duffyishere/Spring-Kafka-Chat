package org.duffy.springinitializr.domain.account;

import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.account.dto.LoginRequestDto;
import org.duffy.springinitializr.domain.account.dto.LoginResponseDto;
import org.duffy.springinitializr.domain.account.dto.RegisterAccountDto;
import org.duffy.springinitializr.domain.auth.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void registerAccount(RegisterAccountDto data) {
        String encryptPassword = passwordEncoder.encode(data.getPassword());
        accountRepository.save(new Account(data, encryptPassword));
    }

    public LoginResponseDto login(LoginRequestDto data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getEmail(),
                        data.getPassword())
        );
        Account user = accountRepository.findByEmail(data.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDto(jwt, refreshToken);
    }
}
