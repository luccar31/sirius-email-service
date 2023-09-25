package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping(value = "/emails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailResponseInfo> sendMail(@RequestBody @Valid MailRequestInfo emailInfo){
        return ResponseEntity.ok(mailService.sendMail(emailInfo));
    }

}
