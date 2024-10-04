package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class LenderToBookInstanceRequest {

    private String name;
    private String description;
    private OffsetDateTime lent;
    private OffsetDateTime dueBack;
    private OffsetDateTime returned;
    private String bookInstanceId;
    private String lenderId;
}
