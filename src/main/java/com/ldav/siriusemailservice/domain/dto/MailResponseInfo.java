package com.ldav.siriusemailservice.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailResponseInfo {
    private String id;
    private String serviceUsed;
    private Object messages;
    private Integer statusCode;

}
