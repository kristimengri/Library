package com.lib.Library.service;

import com.lib.Library.dto.request.BookInstanceRequest;
import com.lib.Library.entity.Book;
import com.lib.Library.entity.BookInstance;
import com.lib.Library.repository.BookInstanceRepository;
import com.lib.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceService {

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookInstance createBookInstance(BookInstanceRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + request.getBookId()));

        BookInstance instance = new BookInstance();
        instance.setBook(book);
        instance.setSerialNumber(request.getSerialNumber());
        instance.setBlocked(request.isBlocked());
        instance.setDateCreated(OffsetDateTime.now());
        instance.setDateUpdated(OffsetDateTime.now());
        instance.setName(book.getName());
        instance.setDescription(book.getDescription());

        return bookInstanceRepository.save(instance);
    }

    public List<BookInstance> getAllBookInstances() {
        return bookInstanceRepository.findAll();
    }

    public Optional<BookInstance> getBookInstanceById(String id) {
        return bookInstanceRepository.findById(id);
    }

    public BookInstance updateBookInstance(String id, BookInstanceRequest request) {
        BookInstance instance = bookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookInstance not found with id: " + id));

        if (request.getSerialNumber() != null) {
            instance.setSerialNumber(request.getSerialNumber());
        }
        instance.setBlocked(request.isBlocked());

        instance.setDateUpdated(OffsetDateTime.now());
        return bookInstanceRepository.save(instance);
    }

    public void deleteBookInstance(String id) {
        BookInstance instance = bookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookInstance not found with id: " + id));
        bookInstanceRepository.deleteById(id);
    }
}
