package org.duffy.springinitializr.domain.account;

import lombok.RequiredArgsConstructor;
import org.duffy.springinitializr.domain.account.dto.LoginRequestDto;
import org.duffy.springinitializr.domain.account.dto.RegisterAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterAccountDto data) {
        accountService.registerAccount(data);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto data) {
        return ResponseEntity.ok(accountService.login(data));
    }
}
