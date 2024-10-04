package com.lib.Library.repository;

import com.lib.Library.entity.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInstanceRepository extends JpaRepository<BookInstance, String> {
}
