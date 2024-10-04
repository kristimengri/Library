package com.lib.Library.dto.response;

import com.lib.Library.entity.LenderToBookInstance;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class LenderToBookInstanceResponse {

    private String id;
    private String name;
    private String description;
    private OffsetDateTime lent;
    private OffsetDateTime dueBack;
    private OffsetDateTime returned;
    private String bookInstanceId;
    private String lenderId;

}
