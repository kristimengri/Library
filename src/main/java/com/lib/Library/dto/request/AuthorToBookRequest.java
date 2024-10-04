package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorToBookRequest {

    private String name;
    private String description;
    private String mainAuthor;
    private String authorId;
    private String bookId;
}
