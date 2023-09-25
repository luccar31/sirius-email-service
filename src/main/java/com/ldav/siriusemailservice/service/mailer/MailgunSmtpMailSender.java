package com.ldav.siriusemailservice.service.mailer;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailgunSmtpMailSender extends AbstractSmtpMailSender {

    @Autowired
    protected MailgunSmtpMailSender(@Qualifier("mailgunJavaMailSender") JavaMailSender javaMailSender){
        super(javaMailSender);
    }

    @Override
    protected void sendEmail(Message message) {

    }

    @Override
    protected MimeMessage prepareSimpleEmail(MailRequestInfo request) {
        return null;
    }

    @Override
    protected MailResponseInfo getEmailDetails() {
        var info = new MailResponseInfo();
        info.setServiceUsed("Mailgun SMTP");
        return info;
    }
}