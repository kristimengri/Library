package com.lib.Library.controller;

import com.lib.Library.dto.request.BookDTO;
import com.lib.Library.dto.request.BookFilterRequest;
import com.lib.Library.dto.request.BookRequest;
import com.lib.Library.dto.response.BookResponse;
import com.lib.Library.entity.Book;
import com.lib.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        Book book = bookService.createBook(bookRequest);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setDescription(book.getDescription());
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable("id") String id, @RequestBody BookRequest bookRequest
    ) {
        Book updatedBook = bookService.updateBook(id, bookRequest);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(id);
        bookResponse.setName(updatedBook.getName());
        bookResponse.setDescription(updatedBook.getDescription());
        return ResponseEntity.ok(bookResponse);
    }


    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteBook(
            @RequestParam String id
    ) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<BookDTO>> filterBooks(@RequestBody BookFilterRequest filterRequest) {
        List<BookDTO> filteredBooks = bookService.filterBooks(
                filterRequest.getNamePattern(),
                filterRequest.getAuthorIds(),
                filterRequest.getGenreIds()
        );
        return ResponseEntity.ok(filteredBooks);
    }

}
