package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInstanceRequest {

    private String bookId;
    private String serialNumber;
    private boolean blocked;
}
