package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookFilterRequest {
    private String namePattern;
    private List<String> authorIds;
    private List<String> genreIds;
}



