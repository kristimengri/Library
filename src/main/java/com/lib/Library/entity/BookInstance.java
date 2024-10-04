package com.lib.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_book_instance_serial_number", columnList = "serialNumber"),
        @Index(name = "index_book_instance_book_id", columnList = "book_id")
})
public class BookInstance extends Basic {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String serialNumber;
    private boolean blocked = false;
}
