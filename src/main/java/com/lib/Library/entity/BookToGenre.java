package com.lib.Library.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_book_to_genre_book_id", columnList = "book_id"),
        @Index(name = "index_book_to_genre_genre_id", columnList = "genre_id")
})
public class BookToGenre extends Basic {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
