package com.lib.Library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_author_name", columnList = "name"),
        @Index(name = "index_author_email", columnList = "email"),
        @Index(name = "index_author_social_security_number", columnList = "socialSecurityNumber")
})
public class Author extends Person {

    @OneToMany(mappedBy = "author")
    private List<AuthorToBook> books;
}
