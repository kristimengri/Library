package com.lib.Library.controller;

import com.lib.Library.entity.Lender;
import com.lib.Library.service.LenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lenders")
public class LenderController {

    @Autowired
    private LenderService lenderService;

    @PostMapping("/createLender")
    public ResponseEntity<Lender> createLender(@RequestBody Lender lender) {
        Lender createdLender = lenderService.createLender(lender);
        return ResponseEntity.ok(createdLender);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Lender>> getAllLenders() {
        List<Lender> lenders = lenderService.getAllLenders();
        return ResponseEntity.ok(lenders);
    }

    @PostMapping("/getById")
    public ResponseEntity<Lender> getLenderById(@RequestParam String id) {
        return lenderService.getLenderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/updateLender")
    public ResponseEntity<Lender> updateLender(@RequestParam String id, @RequestBody Lender lenderRequest) {
        Lender updatedLender = lenderService.updateLender(id, lenderRequest);
        return ResponseEntity.ok(updatedLender);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteLender(@RequestParam String id) {
        lenderService.deleteLender(id);
        return ResponseEntity.noContent().build();
    }
}
