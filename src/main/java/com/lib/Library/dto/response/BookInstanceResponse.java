package com.lib.Library.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInstanceResponse {
    private String id;
    private String serialNumber;
    private boolean blocked;
    private String bookId;
    private String name;
    private String description;
}
