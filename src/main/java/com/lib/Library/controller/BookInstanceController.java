package com.lib.Library.controller;

import com.lib.Library.dto.request.BookInstanceRequest;
import com.lib.Library.dto.response.BookInstanceResponse;
import com.lib.Library.entity.BookInstance;
import com.lib.Library.service.BookInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookInstances")
public class BookInstanceController {

    @Autowired
    private BookInstanceService bookInstanceService;

    @PostMapping("/create")
    public ResponseEntity<BookInstanceResponse> createBookInstance(@RequestBody BookInstanceRequest request) {
        BookInstance instance = bookInstanceService.createBookInstance(request);
        BookInstanceResponse response = new BookInstanceResponse();
        response.setId(instance.getId());
        response.setSerialNumber(instance.getSerialNumber());
        response.setBlocked(instance.isBlocked());
        response.setBookId(instance.getBook().getId());
        response.setName(instance.getName());
        response.setDescription(instance.getDescription());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<List<BookInstanceResponse>> getAllBookInstances() {
        List<BookInstance> instances = bookInstanceService.getAllBookInstances();
        List<BookInstanceResponse> responseList = instances.stream().map(instance -> {
            BookInstanceResponse response = new BookInstanceResponse();
            response.setId(instance.getId());
            response.setSerialNumber(instance.getSerialNumber());
            response.setBlocked(instance.isBlocked());
            response.setBookId(instance.getBook().getId());
            response.setName(instance.getName());
            response.setDescription(instance.getDescription());
            return response;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookInstanceResponse> updateBookInstance(
            @PathVariable("id") String id, @RequestBody BookInstanceRequest request
    ) {
        BookInstance updatedInstance = bookInstanceService.updateBookInstance(id, request);
        BookInstanceResponse response = new BookInstanceResponse();
        response.setId(updatedInstance.getId());
        response.setSerialNumber(updatedInstance.getSerialNumber());
        response.setBlocked(updatedInstance.isBlocked());
        response.setBookId(updatedInstance.getBook().getId());
        response.setName(updatedInstance.getName());
        response.setDescription(updatedInstance.getDescription());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteBookInstance(@RequestParam String id) {
        bookInstanceService.deleteBookInstance(id);
        return ResponseEntity.noContent().build();
    }
}
