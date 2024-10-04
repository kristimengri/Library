package com.lib.Library.service;

import com.lib.Library.dto.request.AuthorRequest;
import com.lib.Library.entity.Author;
import com.lib.Library.repository.AuthorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setDescription(authorRequest.getDescription());
        author.setEmail(authorRequest.getEmail());
        author.getAddress(authorRequest.getAddress());
        author.setGender(authorRequest.getGender());
        author.setBirthDate(authorRequest.getBirthDate());
        author.setSocialSecurityNumber(authorRequest.getSocialSecurityNumber());
        author.setPhoneNumber(authorRequest.getPhoneNumber());
        author.setDateCreated(OffsetDateTime.now());
        author.setDateUpdated(OffsetDateTime.now());
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Author updateAuthor(String id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        if (authorRequest.getName() != null) {
            author.setName(authorRequest.getName());
        }
        if (authorRequest.getEmail() != null) {
            author.setEmail(authorRequest.getEmail());
        }
        if (authorRequest.getPhoneNumber() != null) {
            author.setPhoneNumber(authorRequest.getPhoneNumber());
        }
        author.setDateUpdated(OffsetDateTime.now());

        return authorRepository.save(author);
    }

    public void deleteAuthor(String id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        authorRepository.deleteById(id);
    }

    public List<Author> filterAuthors(String namePattern, String emailPattern) {
        return authorRepository.findAll((Specification<Author>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (namePattern != null && !namePattern.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("name"), "%" + namePattern + "%"));
            }

            if (emailPattern != null && !emailPattern.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("email"), "%" + emailPattern + "%"));
            }

            return predicate;
        });
    }

    public List<Author> findAuthorsByIds(List<String> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

}
