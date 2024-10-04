package com.lib.Library.service;

import com.lib.Library.dto.request.BookDTO;
import com.lib.Library.dto.request.BookRequest;
import com.lib.Library.entity.*;
import com.lib.Library.repository.BookRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;


    public Book createBook(BookRequest bookRequest) {

        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setDescription(bookRequest.getDescription());
        book.setDateCreated(OffsetDateTime.now());
        book.setDateUpdated(OffsetDateTime.now());

        Book savedBook = bookRepository.save(book);


        if (bookRequest.getAuthorIds() != null) {
            for (String authorId : bookRequest.getAuthorIds()) {
                Author author = (Author) authorService.findAuthorsByIds(Collections.singletonList(authorId));

                AuthorToBook authorToBook = new AuthorToBook();
                authorToBook.setBook(savedBook);
                authorToBook.setAuthor(author);
                authorToBook.setDateCreated(OffsetDateTime.now());
                authorToBook.setDateUpdated(OffsetDateTime.now());
            }
        }

        if (bookRequest.getGenreIds() != null) {
            for (String genreId : bookRequest.getGenreIds()) {
                Genre genre = (Genre) genreService.findGenresByIds(Collections.singletonList(genreId));

                BookToGenre bookToGenre = new BookToGenre();
                bookToGenre.setBook(savedBook);
                bookToGenre.setGenre(genre);
                bookToGenre.setDateCreated(OffsetDateTime.now());
                bookToGenre.setDateUpdated(OffsetDateTime.now());

            }
        }
            return savedBook;

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }


    public Book updateBook(String id, BookRequest bookRequest) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        if (bookRequest.getName() != null) {
            book.setName(bookRequest.getName());
        }
        if (bookRequest.getDescription() != null) {
            book.setDescription(bookRequest.getDescription());
        }

        if (bookRequest.getAuthorIds() != null) {
            List<Author> authors = authorService.findAuthorsByIds(bookRequest.getAuthorIds());
        }
        if (bookRequest.getGenreIds() != null) {
            List<Genre> genres = genreService.findGenresByIds(bookRequest.getGenreIds());
        }
        book.setDateUpdated(OffsetDateTime.now());

        return bookRepository.save(book);
    }


    public void deleteBook(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.deleteById(id);
    }


    public List<BookDTO> filterBooks(String namePattern, List<String> authorIds, List<String> genreIds) {
        List<Book> books = bookRepository.findAll((Specification<Book>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (namePattern != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + namePattern + "%"));
            }

            if (authorIds != null && !authorIds.isEmpty()) {
                Join<Book, AuthorToBook> authorJoin = root.join("authors");
                predicate = criteriaBuilder.and(predicate, authorJoin.get("authorId").in(authorIds));
            }

            if (genreIds != null && !genreIds.isEmpty()) {
                Join<Book, BookToGenre> genreJoin = root.join("genres");
                predicate = criteriaBuilder.and(predicate, genreJoin.get("genreId").in(genreIds));
            }

            return predicate;
        });

        return books.stream().map(book -> {
            BookDTO dto = new BookDTO();
            dto.setId(book.getId());
            dto.setName(book.getName());
            dto.setDescription(book.getDescription());
            dto.setAuthorIds(book.getAuthors().stream().map(authorToBook -> authorToBook.getAuthor().getId()).collect(Collectors.toList()));
            dto.setGenreIds(book.getGenres().stream().map(bookToGenre -> bookToGenre.getGenre().getId()).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

}


