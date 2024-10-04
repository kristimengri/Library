package com.lib.Library.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorToBookResponse {

    private String id;
    private String name;
    private String description;
    private String mainAuthor;
    private String authorId;
    private String bookId;
}
