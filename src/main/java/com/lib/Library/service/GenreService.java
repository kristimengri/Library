package com.lib.Library.service;

import com.lib.Library.entity.Genre;
import com.lib.Library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(Genre genre) {
        genre.setDateCreated(OffsetDateTime.now());
        genre.setDateUpdated(OffsetDateTime.now());
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(String id) {
        return genreRepository.findById(id);
    }

    public Genre updateGenre(String id, Genre genreRequest) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));

        if (genreRequest.getName() != null) {
            genre.setName(genreRequest.getName());
        }
        if (genreRequest.getDescription() != null) {
            genre.setDescription(genreRequest.getDescription());
        }

        genre.setDateUpdated(OffsetDateTime.now());
        return genreRepository.save(genre);
    }

    public void deleteGenre(String id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
        genreRepository.deleteById(id);
    }

    public List<Genre> findGenresByIds(List<String> genreIds) {
        return genreRepository.findAllById(genreIds);
    }
}
