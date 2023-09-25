package com.ldav.siriusemailservice.domain.mailing;

import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;

public interface MailSender {
    boolean isAvailable();
    MailSenterType getType();
    MailResponseInfo sendSimpleEmail(MailRequestInfo request);
}
