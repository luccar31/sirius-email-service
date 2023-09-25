package com.ldav.siriusemailservice.domain.dto;

import lombok.Getter;

@Getter
public class UserStats {
    private final String username;
    private final int emailCount;

    public UserStats(String username, int emailCount) {
        this.username = username;
        this.emailCount = emailCount;
    }
}
