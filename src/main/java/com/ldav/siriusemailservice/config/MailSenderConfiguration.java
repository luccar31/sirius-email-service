package com.ldav.siriusemailservice.config;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.postmarkapp.postmark.Postmark;
import com.postmarkapp.postmark.client.ApiClient;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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

    @Bean("mailgunJavaMailSender")
    public JavaMailSender mailgunJavaMailSender(
            @Value("${mailgun.smtp.host}") String host,
            @Value("${mailgun.smtp.port}") Integer port,
            @Value("${mailgun.smtp.username}") String username,
            @Value("${mailgun.smtp.password}") String password
    ) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

}
