package com.ldav.siriusemailservice.factory;

import com.ldav.siriusemailservice.domain.mailing.MailSender;
import com.ldav.siriusemailservice.exception.NotAvailableMailSenderFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailSenderFactory {

    private final List<MailSender> mailSenders;
    @Value("${app.mailsender-type}")
    private String type;

    public MailSender getNextAvailable() {
        return mailSenders.stream()
                .filter(sender -> sender.isAvailable() && sender.getType().name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new NotAvailableMailSenderFoundException("No available mailer of type: " + type + " found for sending mails!"));
    }
}