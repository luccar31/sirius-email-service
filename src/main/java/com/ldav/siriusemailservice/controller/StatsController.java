package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.domain.dto.UserStats;
import com.ldav.siriusemailservice.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping(value = "/stats")
    public ResponseEntity<List<UserStats>> getStats(){
        return ResponseEntity.ok(statsService.getAllUsersStats());
    }
}
