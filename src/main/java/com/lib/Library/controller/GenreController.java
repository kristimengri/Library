package com.lib.Library.controller;

import com.lib.Library.entity.Genre;
import com.lib.Library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre createdGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") String id) {
        Genre genre = genreService.getGenreById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
        return ResponseEntity.ok(genre);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Genre> updateGenre(
            @PathVariable("id") String id, @RequestBody Genre genreRequest) {
        Genre updatedGenre = genreService.updateGenre(id, genreRequest);
        return ResponseEntity.ok(updatedGenre);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteGenre(@RequestParam String id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
