package com.ldav.siriusemailservice.service.mailer;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.domain.mailing.AbstractApiMailSender;
import com.ldav.siriusemailservice.exception.ApiServerException;
import com.ldav.siriusemailservice.exception.ClientServerException;
import com.ldav.siriusemailservice.exception.MailSenderException;
import com.postmarkapp.postmark.client.ApiClient;
import com.postmarkapp.postmark.client.data.model.message.Message;
import com.postmarkapp.postmark.client.data.model.message.MessageResponse;
import com.postmarkapp.postmark.client.exception.InvalidAPIKeyException;
import com.postmarkapp.postmark.client.exception.InvalidMessageException;
import com.postmarkapp.postmark.client.exception.PostmarkException;
import com.postmarkapp.postmark.client.exception.TimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostmarkMailSender extends AbstractApiMailSender<Message, MessageResponse> {

    private final ApiClient api;

    @Value("${postmark.from}")
    private String from;

    @Override
    protected MessageResponse sendEmail(Message message) throws Exception{
        return api.deliverMessage(message);
    }

    @Override
    protected Message prepareEmail(MailRequestInfo request) {
        var message = new Message();
        message.setFrom(from);
        if(request.getBcc() != null){
            message.setBcc(request.getBcc());
        }
        if(request.getCc() != null){
            message.setCc(request.getCc());
        }
        message.setTo(request.getTo());
        if(request.getSubject() != null){
            message.setSubject(request.getSubject());
        }
        else{
            message.setSubject("");
        }
        message.setHtmlBody(request.getText());
        return message;
    }

    @Override
    protected MailResponseInfo getEmailDetails(MessageResponse response) {
        var info = new MailResponseInfo();
        info.setId(response.getMessageId());
        info.setServiceUsed("Postmark Api");
        info.setStatusCode(response.getErrorCode());
        info.setMessages(response.getMessage());
        return info;
    }

    @Override
    protected MailSenderException manageSpecificExceptions(Exception e) {

        MailSenderException toThrow = null;

        if(e instanceof InvalidAPIKeyException){
            toThrow = new ClientServerException("Invalid Postmark api key", e, HttpStatus.UNAUTHORIZED);
        }

        if(e instanceof InvalidMessageException){
            toThrow = new ClientServerException("Invalid message format for Postmark", e, HttpStatus.BAD_REQUEST);
        }

        if(e instanceof TimeoutException){
            toThrow = new ApiServerException("Postmark server timeout", e, HttpStatus.REQUEST_TIMEOUT);
        }

        if(e instanceof PostmarkException && toThrow == null){
            toThrow = new ApiServerException("Error with Postmark server", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return toThrow;
    }
}
