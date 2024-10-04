package com.lib.Library.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class BookRequest {

    private String name;
    private String description;
    private List<String> authorIds;
    private List<String> genreIds;

}
