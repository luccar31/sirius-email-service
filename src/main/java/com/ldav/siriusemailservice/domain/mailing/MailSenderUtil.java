package com.ldav.siriusemailservice.domain.mailing;

public class MailSenderUtil {

    public static String getDomain(String email){
        return email.split("@")[1];
    }
}
