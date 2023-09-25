package com.ldav.siriusemailservice.service;

import com.ldav.siriusemailservice.domain.dto.LoginRequest;
import com.ldav.siriusemailservice.domain.dto.LoginResponse;
import com.ldav.siriusemailservice.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var jwtToken = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return new LoginResponse(jwtToken);
    }
}
