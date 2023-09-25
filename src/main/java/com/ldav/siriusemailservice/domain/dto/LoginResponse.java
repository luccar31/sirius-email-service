package com.ldav.siriusemailservice.domain.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String token;

    public LoginResponse(String token){
        this.token = token;
    }
}