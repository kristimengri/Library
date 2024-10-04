package com.lib.Library.controller;

import com.lib.Library.dto.request.AuthorFilterRequest;
import com.lib.Library.dto.request.AuthorRequest;
import com.lib.Library.entity.Author;
import com.lib.Library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/createAuthor")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) {
        Author author = authorService.createAuthor(authorRequest);
        return ResponseEntity.ok(author);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable("id") String id, @RequestBody AuthorRequest authorRequest
    ) {
        Author updatedAuthor = authorService.updateAuthor(id, authorRequest);
        return ResponseEntity.ok(updatedAuthor);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteAuthor(@RequestParam String id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Author>> filterAuthors(@RequestBody AuthorFilterRequest filterRequest) {
        List<Author> filteredAuthors = authorService.filterAuthors(
                filterRequest.getNamePattern(), filterRequest.getEmailPattern());
        return ResponseEntity.ok(filteredAuthors);
    }
}
