package com.ldav.siriusemailservice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailRequestInfo {

    //@NotNull(message = "Sender's email address must be present")
    //@Email(message = "Must be an email address")
    //private String from;

    @NotNull(message = "Receiver(s) email(s) must be present")
    @Size(min = 1, message = "At least one receiver email must be present")
    private List<@Email(message = "Must be an email address") String> to;

    @Size(min = 1, message = "At least one receiver email must be present")
    private List<@Email(message = "Must be an email address") String> cc;

    @Size(min = 1, message = "At least one receiver email must be present")
    private List<@Email(message = "Must be an email address") String> bcc;

    @NotBlank(message = "Subject must contain something other than whitespace characters")
    private String subject;

    @NotNull(message = "Content must be present")
    private String text;
}
