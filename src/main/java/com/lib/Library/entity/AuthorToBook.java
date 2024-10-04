package com.lib.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_author_to_book_author_id", columnList = "author_id"),
        @Index(name = "index_author_to_book_book_id", columnList = "book_id")
})
public class AuthorToBook extends Basic {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private boolean mainAuthor;
}
