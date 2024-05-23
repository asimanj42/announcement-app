package az.ingress.announcementapp.service.auth;

import az.ingress.announcementapp.dto.auth.LoginRequest;
import az.ingress.announcementapp.dto.auth.LoginResponse;
import az.ingress.announcementapp.dto.auth.RegisterRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);

    void activate(String email, Integer verificationCode);

    void resendVerificationCode(String email);

}
