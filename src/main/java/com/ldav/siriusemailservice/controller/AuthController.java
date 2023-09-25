package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.domain.dto.LoginRequest;
import com.ldav.siriusemailservice.domain.dto.LoginResponse;
import com.ldav.siriusemailservice.domain.dto.SignupForm;
import com.ldav.siriusemailservice.service.AuthenticationService;
import com.ldav.siriusemailservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupForm form) throws URISyntaxException {
        var user = userService.create(form);
        var resourceLocator = new URI("http://localhost:8080/users?" + user.getUsername());
        return ResponseEntity.created(resourceLocator).build();
    }
}
