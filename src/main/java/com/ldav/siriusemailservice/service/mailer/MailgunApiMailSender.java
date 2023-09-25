package com.ldav.siriusemailservice.service.mailer;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.domain.mailing.AbstractApiMailSender;
import com.ldav.siriusemailservice.domain.mailing.MailSenderUtil;
import com.ldav.siriusemailservice.exception.MailSenderException;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailgunApiMailSender extends AbstractApiMailSender<Message, MessageResponse> {

    private final MailgunMessagesApi api;

    @Value("${mailgun.from}")
    private String from;

    @Override
    protected MessageResponse sendEmail(Message message) {
        return api.sendMessage(MailSenderUtil.getDomain(from), message);
    }

    @Override
    protected Message prepareEmail(MailRequestInfo request) {
        return Message.builder()
                .from(from)
                .to(request.getTo())
                .bcc(request.getBcc())
                .cc(request.getCc())
                .subject(request.getSubject() == null ? "" : request.getSubject())
                .text(request.getText())
                .build();
    }

    protected MailResponseInfo getEmailDetails(MessageResponse response){
        var info = new MailResponseInfo();
        info.setId(response.getId());
        info.setServiceUsed("Mailgun Api");
        info.setMessages(response.getMessage());
        return info;
    }

    @Override
    protected MailSenderException manageSpecificExceptions(Exception e) {
        return new MailSenderException(e);
    }
}
