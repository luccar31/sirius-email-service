package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.controller.documentation.UsersApi;
import com.ldav.siriusemailservice.domain.dto.SignupForm;
import com.ldav.siriusemailservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Validated
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UserService userService;

    @SneakyThrows
    public ResponseEntity<?> signup(SignupForm form) {
        var user = userService.create(form);
        var resourceLocator = new URI("http://localhost:8080/users?" + user.getUsername());
        return ResponseEntity.created(resourceLocator).build();
    }
}
