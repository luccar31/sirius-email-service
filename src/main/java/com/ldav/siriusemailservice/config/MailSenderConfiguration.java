package com.ldav.siriusemailservice.config;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.postmarkapp.postmark.Postmark;
import com.postmarkapp.postmark.client.ApiClient;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailSenderConfiguration {

    @Bean
    public MailgunMessagesApi mailgunMessagesApi(@Value("${mailgun.apikey}") String apikey) {
        return MailgunClient.config(apikey)
                .createApi(MailgunMessagesApi.class);
    }

    @Bean
    public ApiClient postmarkApi(@Value("${postmark.apikey}") String apikey){
        return Postmark.getApiClient(apikey);
    }

    @Bean
    public SendGrid sendGridApi(@Value("${sendgrid.apikey}") String apikey){
        return new SendGrid(apikey);
    }

}
