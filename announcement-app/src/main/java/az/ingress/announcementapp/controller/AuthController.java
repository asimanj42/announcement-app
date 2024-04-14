package az.ingress.announcementapp.controller;

import az.ingress.announcementapp.dto.auth.LoginRequest;
import az.ingress.announcementapp.dto.auth.LoginResponse;
import az.ingress.announcementapp.dto.auth.RegisterRequest;
import az.ingress.announcementapp.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
