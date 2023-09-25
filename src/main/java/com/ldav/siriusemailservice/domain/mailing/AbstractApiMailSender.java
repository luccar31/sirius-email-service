package com.ldav.siriusemailservice.domain.mailing;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import com.ldav.siriusemailservice.exception.MailSenderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractApiMailSender<M,R> implements MailSender {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected boolean available = true;

    @Override
    public MailResponseInfo sendSimpleEmail(MailRequestInfo request) {
        try{
            logger.info("Trying sending a mail with: " + this.getClass().getSimpleName());
            var message = prepareEmail(request);
            return getEmailDetails(sendEmail(message));
        }
        catch (Exception e){
            //api becomes no longer available to send mails if an error occurs
            available = false;
            logger.error("Error ocurred with: " + this.getClass().getSimpleName(), e);
            throw manageSpecificExceptions(e);
        }
    }

    /*
    @Override
    public MailResponseInfo sendEmailWithAttachments(MailRequestInfo request, List<File> attachments) {
        throw new NotImplementedException();
    }
     */

    protected abstract R sendEmail(M message) throws Exception;
    protected abstract M prepareEmail(MailRequestInfo request) throws Exception;
    protected abstract MailResponseInfo getEmailDetails(R response) throws Exception;

    protected abstract MailSenderException manageSpecificExceptions(Exception e);

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public final MailSenterType getType(){
        return MailSenterType.API;
    }

}
