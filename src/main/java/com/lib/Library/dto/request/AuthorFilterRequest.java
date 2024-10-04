package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorFilterRequest {
    private String namePattern;
    private String emailPattern;
}
