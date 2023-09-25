package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.controller.documentation.AuthenticacionApi;
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
@Validated
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticacionApi {

    private final AuthenticationService authenticationService;

    public ResponseEntity<LoginResponse> login(LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
