package com.example.smart_money_manager.service;

import com.example.smart_money_manager.dto.AuthResponse;
import com.example.smart_money_manager.dto.LoginRequest;
import com.example.smart_money_manager.dto.RegisterRequest;
import com.example.smart_money_manager.model.Role;
import com.example.smart_money_manager.model.User;
import com.example.smart_money_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getUsername()); // or getEmail(), whatever uniquely identifies the user
;
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user.getUsername()); // or getEmail(), whatever uniquely identifies the user
        return new AuthResponse(jwtToken);
    }
}
