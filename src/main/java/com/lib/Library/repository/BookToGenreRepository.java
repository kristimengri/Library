package com.lib.Library.repository;

import com.lib.Library.entity.BookToGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToGenreRepository extends JpaRepository<BookToGenre, String> {
}
