package com.lib.Library.repository;

import com.lib.Library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepository extends JpaRepository<Author, String> , JpaSpecificationExecutor<Author> {
}
