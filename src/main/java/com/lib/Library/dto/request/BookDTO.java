package com.lib.Library.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookDTO {
    private String id;
    private String name;
    private String description;
    private List<String> authorIds;
    private List<String> genreIds;
}
