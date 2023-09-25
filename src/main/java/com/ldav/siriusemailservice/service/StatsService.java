package com.ldav.siriusemailservice.service;

import com.ldav.siriusemailservice.domain.dto.UserStats;
import com.ldav.siriusemailservice.domain.mailing.MailCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final MailCounter mailCounter;

    public List<UserStats> getAllUsersStats(){
        return mailCounter.getRegister()
                .stream()
                .map(entry -> new UserStats(entry.getKey(), entry.getValue()))
                .toList();
    }

}
