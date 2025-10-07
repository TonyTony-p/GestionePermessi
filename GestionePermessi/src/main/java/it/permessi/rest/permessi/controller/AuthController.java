package it.permessi.rest.permessi.controller;

import it.permessi.rest.permessi.dto.LoginRequest;
import it.permessi.rest.permessi.dto.LoginResponse;
import it.permessi.rest.permessi.dto.RegisterRequest;
import it.permessi.rest.permessi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** Controller REST per autenticazione: delega tutta la logica ad AuthService. */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
    }
}
