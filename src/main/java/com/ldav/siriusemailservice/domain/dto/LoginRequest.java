package com.ldav.siriusemailservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull(message = "Username must be present")
    private String username;
    @NotNull(message = "Password must be present")
    private String password;
}
