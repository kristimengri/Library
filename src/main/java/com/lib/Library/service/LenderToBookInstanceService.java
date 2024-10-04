package com.lib.Library.service;

import com.lib.Library.dto.request.LenderToBookInstanceRequest;
import com.lib.Library.entity.BookInstance;
import com.lib.Library.entity.Lender;
import com.lib.Library.entity.LenderToBookInstance;
import com.lib.Library.repository.BookInstanceRepository;
import com.lib.Library.repository.LenderRepository;
import com.lib.Library.repository.LenderToBookInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LenderToBookInstanceService {

    @Autowired
    private LenderToBookInstanceRepository lenderToBookInstanceRepository;

    @Autowired
    private LenderRepository lenderRepository;

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    public LenderToBookInstance createLenderToBookInstance(LenderToBookInstanceRequest lenderToBookInstanceRequest) {

        LenderToBookInstance lenderToBookInstance = new LenderToBookInstance();
        lenderToBookInstance.setDateCreated(OffsetDateTime.now());
        lenderToBookInstance.setDateUpdated(OffsetDateTime.now());
        lenderToBookInstance.setDueBack(lenderToBookInstanceRequest.getDueBack());
        lenderToBookInstance.setLent(lenderToBookInstanceRequest.getLent());
        lenderToBookInstance.setReturned(lenderToBookInstanceRequest.getReturned());
        lenderToBookInstance.setDescription(lenderToBookInstanceRequest.getDescription());
        Lender lender = lenderRepository.findById(lenderToBookInstanceRequest.getLenderId()).orElseThrow(() -> new RuntimeException("Lender not found with id: " + lenderToBookInstanceRequest.getLenderId()));
        BookInstance bookInstance = bookInstanceRepository.findById(lenderToBookInstanceRequest.getBookInstanceId()).orElseThrow(() -> new RuntimeException("BookInstance not found with id: " + lenderToBookInstanceRequest.getBookInstanceId()));
        lenderToBookInstance.setBookInstance(bookInstance);
        lenderToBookInstance.setLender(lender);

        return lenderToBookInstanceRepository.save(lenderToBookInstance);
    }

    public Optional<LenderToBookInstance> getLenderToBookInstanceById(String id) {
        return lenderToBookInstanceRepository.findById(id);
    }

    public List<LenderToBookInstance> getAllLenderToBookInstances() {
        return lenderToBookInstanceRepository.findAll();
    }

    public LenderToBookInstance updateLenderToBookInstance(String id, LenderToBookInstanceRequest lenderToBookInstanceRequest) {
        LenderToBookInstance existing = lenderToBookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LenderToBookInstance not found with id: " + id));

        if (lenderToBookInstanceRequest.getLent() != null) {
            existing.setLent(lenderToBookInstanceRequest.getLent());
        }
        if (lenderToBookInstanceRequest.getDueBack() != null) {
            existing.setDueBack(lenderToBookInstanceRequest.getDueBack());
        }
        if (lenderToBookInstanceRequest.getReturned() != null) {
            existing.setReturned(lenderToBookInstanceRequest.getReturned());
        }

        existing.setDateUpdated(OffsetDateTime.now());

        return lenderToBookInstanceRepository.save(existing);
    }

    public void deleteLenderToBookInstance(String id) {
        LenderToBookInstance lenderToBookInstance = lenderToBookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LenderToBookInstance not found with id: " + id));
        lenderToBookInstanceRepository.delete(lenderToBookInstance);
    }
}
