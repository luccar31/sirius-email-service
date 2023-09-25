package com.ldav.siriusemailservice.service;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.domain.mailing.MailCounter;
import com.ldav.siriusemailservice.exception.ApiException;
import com.ldav.siriusemailservice.exception.MailLimitReachedException;
import com.ldav.siriusemailservice.exception.NotAvailableMailSenderFoundException;
import com.ldav.siriusemailservice.factory.MailSenderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSenderFactory mailSenderFactory;
    private final MailCounter mailCounter;

    @Retryable(
            retryFor = ApiException.class,
            noRetryFor = {NotAvailableMailSenderFoundException.class, MailLimitReachedException.class},
            maxAttempts = 5, backoff = @Backoff(delay = 1000)
    )
    public MailResponseInfo sendMail(MailRequestInfo request){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(mailCounter.getCounterFor(userDetails.getUsername()) >= 1000){
            throw new MailLimitReachedException("Mail limit reached for: " + userDetails.getUsername());
        }

        var mailer = mailSenderFactory.getNextAvailable();
        return mailer.sendSimpleEmail(request);
    }

}
