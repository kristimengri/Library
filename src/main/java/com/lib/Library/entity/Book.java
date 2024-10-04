package com.lib.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_book_name", columnList = "name"),
        @Index(name = "index_book_id", columnList = "id"),
        @Index(name = "index_book_date_created", columnList = "dateCreated"),
        @Index(name = "index_book_soft_delete", columnList = "softDelete")
})
public class Book extends Basic {

    @OneToMany
    @JoinColumn(name = "book_id")
    private List<BookToGenre> genres;

    @OneToMany(mappedBy = "book")
    private List<AuthorToBook> authors;
}
