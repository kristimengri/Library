package com.lib.Library.repository;

import com.lib.Library.entity.AuthorToBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorToBookRepository extends JpaRepository<AuthorToBook, String> {
}
