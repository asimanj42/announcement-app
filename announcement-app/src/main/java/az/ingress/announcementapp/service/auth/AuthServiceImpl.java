package az.ingress.announcementapp.service.auth;

import az.ingress.announcementapp.dto.auth.LoginRequest;
import az.ingress.announcementapp.dto.auth.LoginResponse;
import az.ingress.announcementapp.dto.auth.RegisterRequest;
import az.ingress.announcementapp.entity.Authority;
import az.ingress.announcementapp.entity.User;
import az.ingress.announcementapp.exception.type.BaseException;
import az.ingress.announcementapp.repository.AuthorityRepository;
import az.ingress.announcementapp.repository.UserRepository;
import az.ingress.announcementapp.service.jwt.JwtService;
import az.ingress.announcementapp.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static az.ingress.announcementapp.constants.AppConstants.ROLE_USER;
import static az.ingress.announcementapp.dto.enums.response.ErrorMessages.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final JwtService jwtService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest registerRequest) {

        log.info("User: {}", userRepository.findByUsername(registerRequest.getUsername()));
        checkAccountExisting(registerRequest);

        var userRole = authorityRepository.findByAuthority(ROLE_USER)
                .orElseGet(
                        () -> authorityRepository.save(
                                Authority.builder()
                                        .authority(ROLE_USER)
                                        .build()
                        )
                );

        var user = mapRegisterRequestToUserEntity(registerRequest, userRole);
        userRepository.save(user);
        mailService.sendEmail(user.getUsername(), user.getVerificationCode());
    }


    @Override
    public void activate(String email, Integer verificationCode) {
        userRepository.findByUsername(email)
                .map(user -> {
                    if (!checkVerificationCodeEquality(verificationCode, user)) {
                        user.setVerificationCode(null);
                        userRepository.save(user);
                        throw BaseException.of(VERIFICATION_FAILED);
                    }
                    user.setVerificationCode(null);
                    user.setEnabled(true);
                    user.setAccountNonExpired(true);
                    user.setAccountNonLocked(true);
                    user.setCredentialsNonExpired(true);
                    return userRepository.save(user);
                });
    }


    @Override
    @SneakyThrows
    @Transactional
    public void resendVerificationCode(String email) {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> BaseException.of(ACCOUNT_NOT_FOUND));
        user.setVerificationCode(generateOtpCode());
        userRepository.save(user);
        mailService.sendEmail(email, user.getVerificationCode());
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> BaseException.of(ACCOUNT_NOT_FOUND));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(prepareClaims(user));
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Map<String, Object> prepareClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("username", user.getUsername());
        claims.put("enable", user.isEnabled());
        claims.put("roles", List.of(user.getAuthorities()));
        return claims;
    }

    private boolean checkVerificationCodeEquality(Integer verificationCode, User user) {
        return user.getVerificationCode().equals(verificationCode);
    }

    private void checkAccountExisting(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw BaseException.of(ACCOUNT_ALREADY_EXISTS);
        }

    }

    private User mapRegisterRequestToUserEntity(RegisterRequest registerRequest, Authority authority) {
        return User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .verificationCode(generateOtpCode())
                .authorities(Set.of(authority))
                .build();
    }

    private Integer generateOtpCode() {
        int min = 1001;
        int max = 9999;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }


}
