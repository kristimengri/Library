package com.lib.Library.service;

import com.lib.Library.dto.request.AuthorToBookRequest;
import com.lib.Library.entity.Author;
import com.lib.Library.entity.AuthorToBook;
import com.lib.Library.entity.Book;
import com.lib.Library.repository.AuthorRepository;
import com.lib.Library.repository.AuthorToBookRepository;
import com.lib.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorToBookService {

    @Autowired
    private AuthorToBookRepository authorToBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorToBook createAuthorToBook(AuthorToBookRequest authorToBookRequest) {
        AuthorToBook authorToBook = new AuthorToBook();
        authorToBook.setDateCreated(OffsetDateTime.now());
        authorToBook.setDateUpdated(OffsetDateTime.now());

        authorToBook.setMainAuthor(Boolean.parseBoolean(authorToBookRequest.getMainAuthor()));

        Author author = authorRepository.findById(authorToBookRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorToBookRequest.getAuthorId()));

        Book book = bookRepository.findById(authorToBookRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + authorToBookRequest.getBookId()));

        authorToBook.setAuthor(author);
        authorToBook.setBook(book);

        return authorToBookRepository.save(authorToBook);
    }

    public Optional<AuthorToBook> getAuthorToBookById(String id) {
        return authorToBookRepository.findById(id);
    }

    public List<AuthorToBook> getAllAuthorToBooks() {
        return authorToBookRepository.findAll();
    }

    public AuthorToBook updateAuthorToBook(String id, AuthorToBookRequest authorToBookRequest) {
        AuthorToBook existing = authorToBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuthorToBook not found with id: " + id));

        existing.setMainAuthor(Boolean.parseBoolean(authorToBookRequest.getMainAuthor()));
        existing.setDateUpdated(OffsetDateTime.now());

        return authorToBookRepository.save(existing);
    }

    public void deleteAuthorToBook(String id) {
        AuthorToBook authorToBook = authorToBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuthorToBook not found with id: " + id));
        authorToBookRepository.delete(authorToBook);
    }
}
