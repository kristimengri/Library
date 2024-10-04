package com.lib.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_lender_to_book_instance_lender_id", columnList = "lender_id"),
        @Index(name = "index_lender_to_book_instance_book_instance_id", columnList = "book_instance_id"),
        @Index(name = "index_lender_to_book_instance_due_back", columnList = "dueBack"),
        @Index(name = "index_lender_to_book_instance_returned", columnList = "returned")
})
public class LenderToBookInstance extends Basic {

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private Lender lender;

    @ManyToOne
    @JoinColumn(name = "book_instance_id")
    private BookInstance bookInstance;

    private OffsetDateTime lent;
    private OffsetDateTime dueBack;
    private OffsetDateTime returned;

}
