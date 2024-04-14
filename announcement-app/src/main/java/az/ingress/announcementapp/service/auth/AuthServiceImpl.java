package az.ingress.announcementapp.service.auth;

import az.ingress.announcementapp.dto.auth.LoginRequest;
import az.ingress.announcementapp.dto.auth.LoginResponse;
import az.ingress.announcementapp.dto.auth.RegisterRequest;
import az.ingress.announcementapp.entity.Role;
import az.ingress.announcementapp.entity.User;
import az.ingress.announcementapp.repository.RoleRepository;
import az.ingress.announcementapp.repository.UserRepository;
import az.ingress.announcementapp.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest registerRequest) {
        var userRole = roleRepository.findByName("USER")
                // TODO: better exception handling
                .orElseThrow(() -> new RuntimeException("Role not found"));
        var user = mapRegisterRequestToUserEntity(registerRequest, userRole);
        userRepository.save(user);
        return "User registered successfully";
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = (User) auth.getPrincipal();
        claims.put("fullName", user.fullName());
        var jwtToken = jwtService.createToken(claims, user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    private User mapRegisterRequestToUserEntity(RegisterRequest registerRequest, Role userRole) {
        return User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .age(registerRequest.getAge())
                .roles(List.of(userRole))
                .build();
    }


}
