package com.ldav.siriusemailservice.service.mailer;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.domain.mailing.MailSender;
import com.ldav.siriusemailservice.domain.mailing.MailSenterType;
import com.ldav.siriusemailservice.exception.MailSenderException;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.util.List;

public abstract class AbstractSmtpMailSender implements MailSender {

    protected boolean available = true;
    protected final JavaMailSender mailSender;

    protected AbstractSmtpMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MailResponseInfo sendSimpleEmail(MailRequestInfo request) {
        try{
            var message = prepareSimpleEmail(request);
            sendEmail(message);
            return getEmailDetails();
        }
        catch (Exception e){
            throw new MailSenderException(e);
        }
    }

    protected abstract void sendEmail(Message message);
    protected abstract Message prepareSimpleEmail(MailRequestInfo request);

    protected abstract MailResponseInfo getEmailDetails();

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public MailSenterType getType(){
        return MailSenterType.SMTP;
    }
}
