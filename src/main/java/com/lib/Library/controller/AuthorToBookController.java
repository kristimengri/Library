package com.lib.Library.controller;


import com.lib.Library.dto.request.AuthorToBookRequest;
import com.lib.Library.dto.response.AuthorToBookResponse;
import com.lib.Library.entity.AuthorToBook;
import com.lib.Library.service.AuthorToBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authorToBook")
public class AuthorToBookController {

    @Autowired
    private AuthorToBookService authorToBookService;

    @PostMapping("/create")
    public ResponseEntity<AuthorToBookResponse> createAuthorToBook(
            @RequestBody AuthorToBookRequest authorToBookRequest) {


        AuthorToBook created = authorToBookService.createAuthorToBook(authorToBookRequest);
        AuthorToBookResponse authorToBookResponse = new AuthorToBookResponse();
        authorToBookResponse.setId(created.getId());
        authorToBookResponse.setAuthorId(created.getAuthor().getId());
        authorToBookResponse.setBookId(created.getBook().getId());
        authorToBookResponse.setMainAuthor(String.valueOf(created.isMainAuthor()));
        authorToBookResponse.setName(authorToBookRequest.getName());
        authorToBookResponse.setDescription(authorToBookRequest.getDescription());
        return ResponseEntity.ok(authorToBookResponse);
    }

    @PostMapping("/getById")
    public ResponseEntity<AuthorToBookResponse> getAuthorToBookById(@RequestParam String id) {
        Optional<AuthorToBook> authorToBook = authorToBookService.getAuthorToBookById(id);

        // Check if the entity is present
        if (authorToBook.isPresent()) {
            AuthorToBookResponse response = convertToAuthorToBookResponse(authorToBook.get()); // You'll need to implement this conversion method
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if not found
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<AuthorToBookResponse>> getAllAuthorToBooks() {
        List<AuthorToBook> authorToBooks = authorToBookService.getAllAuthorToBooks();

        // Convert to response DTOs
        List<AuthorToBookResponse> responseList = authorToBooks.stream()
                .map(this::convertToAuthorToBookResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorToBookResponse> updateAuthorToBook(
            @PathVariable("id") String id, @RequestBody AuthorToBookRequest authorToBookRequest) {

        AuthorToBook updated = authorToBookService.updateAuthorToBook(id, authorToBookRequest);

        AuthorToBookResponse authorToBookResponse = new AuthorToBookResponse();
        authorToBookResponse.setId(updated.getId());
        authorToBookResponse.setAuthorId(updated.getAuthor().getId());
        authorToBookResponse.setBookId(updated.getBook().getId());
        authorToBookResponse.setMainAuthor(String.valueOf(updated.isMainAuthor()));

        return ResponseEntity.ok(authorToBookResponse);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteAuthorToBook(@RequestParam String id) {
        authorToBookService.deleteAuthorToBook(id);
        return ResponseEntity.noContent().build();
    }

    private AuthorToBookResponse convertToAuthorToBookResponse(AuthorToBook authorToBook) {
        AuthorToBookResponse response = new AuthorToBookResponse();
        response.setId(authorToBook.getId());

        // Add null checks for author and book
        if (authorToBook.getAuthor() != null) {
            response.setAuthorId(authorToBook.getAuthor().getId());
        } else {
            response.setAuthorId(null);
        }

        if (authorToBook.getBook() != null) {
            response.setBookId(authorToBook.getBook().getId());
        } else {
            response.setBookId(null);
        }

        response.setMainAuthor(String.valueOf(authorToBook.isMainAuthor()));
        return response;
    }
}
