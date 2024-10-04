package com.lib.Library.service;

import com.lib.Library.entity.Lender;
import com.lib.Library.repository.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LenderService {

    @Autowired
    private LenderRepository lenderRepository;

    public Lender createLender(Lender lender) {
        lender.setDateCreated(OffsetDateTime.now());
        lender.setDateUpdated(OffsetDateTime.now());
        return lenderRepository.save(lender);
    }

    public List<Lender> getAllLenders() {
        return lenderRepository.findAll();
    }

    public Optional<Lender> getLenderById(String id) {
        return lenderRepository.findById(id);
    }

    public Lender updateLender(String id, Lender lenderRequest) {
        Lender lender = lenderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lender not found with id: " + id));

        if (lenderRequest.getName() != null) {
            lender.setName(lenderRequest.getName());
        }
        if (lenderRequest.getEmail() != null) {
            lender.setEmail(lenderRequest.getEmail());
        }
        if (lenderRequest.getAddress() != null) {
            lender.setAddress(lenderRequest.getAddress());
        }

        lender.setBlocked(lenderRequest.isBlocked());
        lender.setDateUpdated(OffsetDateTime.now());
        return lenderRepository.save(lender);
    }

    public void deleteLender(String id) {
        Lender lender = lenderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lender not found with id: " + id));
        lenderRepository.deleteById(id);
    }
}
