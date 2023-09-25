package com.ldav.siriusemailservice.service.mailer;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.domain.mailing.AbstractApiMailSender;
import com.ldav.siriusemailservice.exception.ApiServerException;
import com.ldav.siriusemailservice.exception.ClientServerException;
import com.ldav.siriusemailservice.exception.MailSenderException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
@RequiredArgsConstructor
public class SendGridMailSender extends AbstractApiMailSender<Request, Response> {

    private final SendGrid api;

    @Value("${sendgrid.from}")
    private String from;

    @Override
    protected Response sendEmail(Request message) throws Exception {
        return api.api(message);
    }

    @Override
    protected Request prepareEmail(MailRequestInfo request) throws Exception {
        var personalization = new Personalization();

        for(String to : request.getTo()){
            personalization.addTo(new Email(to));
        }

        if(request.getBcc() != null){
            for(String bcc : request.getBcc()){
                personalization.addBcc(new Email(bcc));
            }
        }

        if(request.getCc() != null){
            for(String cc : request.getCc()){
                personalization.addCc(new Email(cc));
            }
        }

        if(request.getSubject() != null){
            personalization.setSubject(request.getSubject());
        }
        else{
            personalization.setSubject("");
        }

        var mail = new Mail();
        mail.setFrom(new Email(from));
        mail.addPersonalization(personalization);
        mail.addContent(new Content("text/html", request.getText()));
        var apiRequest = new Request();
        apiRequest.setMethod(Method.POST);
        apiRequest.setEndpoint("mail/send");
        apiRequest.setBody(mail.build());

        return apiRequest;
    }

    @Override
    protected MailResponseInfo getEmailDetails(Response response) throws Exception {
        var status = HttpStatus.resolve(response.getStatusCode());

        if(status != null && status.is4xxClientError()){
           throw new ClientServerException("Error in this api server", status);
        }

        if(status != null && status.is5xxServerError()){
            throw new ApiServerException("Error in api server", status);
        }

        var info = new MailResponseInfo();
        info.setStatusCode(response.getStatusCode());
        info.setServiceUsed("SendGrid Api");
        info.setMessages(response.getBody());
        return info;
    }

    @Override
    protected MailSenderException manageSpecificExceptions(Exception e) {
        MailSenderException toThrow = null;

        if (e instanceof IOException){
            toThrow = new ClientServerException("Invalid message format", e, HttpStatus.BAD_REQUEST);
        }

        if(e instanceof RateLimitException){
            toThrow = new ApiServerException("SendGrid email limit reached", e, HttpStatus.TOO_MANY_REQUESTS);
        }

        return toThrow != null ? toThrow : new MailSenderException(e);
    }
}
