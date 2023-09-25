package com.ldav.siriusemailservice.domain.mailing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MailCounter {
    private static final Map<String, Integer> COUNTER_MAP = new HashMap<>();

    public void incrementCounterFor(String username){
        int mailCount = COUNTER_MAP.getOrDefault(username, 0);
        COUNTER_MAP.put(username, mailCount + 1);
    }

    public int getCounterFor(String username){
        return COUNTER_MAP.getOrDefault(username, 0);
    }

    public Set<Map.Entry<String, Integer>> getRegister(){
        return COUNTER_MAP.entrySet();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetAllCounters(){
        COUNTER_MAP.clear();
    }
}
