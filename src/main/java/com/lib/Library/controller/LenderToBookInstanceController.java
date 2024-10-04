package com.lib.Library.controller;

import com.lib.Library.dto.request.LenderToBookInstanceRequest;
import com.lib.Library.dto.response.LenderToBookInstanceResponse;
import com.lib.Library.entity.LenderToBookInstance;
import com.lib.Library.service.LenderToBookInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lenderToBookInstance")
public class LenderToBookInstanceController {

    @Autowired
    private LenderToBookInstanceService lenderToBookInstanceService;

    @PostMapping("/create")
    public ResponseEntity<LenderToBookInstanceResponse> createLenderToBookInstance(
            @RequestBody LenderToBookInstanceRequest lenderToBookInstanceRequest) {
        LenderToBookInstance created = lenderToBookInstanceService.createLenderToBookInstance(lenderToBookInstanceRequest);
        LenderToBookInstanceResponse lenderToBookInstanceResponse = new LenderToBookInstanceResponse();
        lenderToBookInstanceResponse.setId(created.getId());
        lenderToBookInstanceResponse.setLenderId(created.getLender().getId());
        lenderToBookInstanceResponse.setBookInstanceId(created.getBookInstance().getId());
        lenderToBookInstanceResponse.setName(created.getName());
        lenderToBookInstanceResponse.setDescription(created.getDescription());
        lenderToBookInstanceResponse.setLent(created.getLent());
        lenderToBookInstanceResponse.setReturned(created.getReturned());
        lenderToBookInstanceResponse.setDueBack(created.getDueBack());


        return ResponseEntity.ok(lenderToBookInstanceResponse);
    }

    @PostMapping("/getById")
    public ResponseEntity<LenderToBookInstanceResponse> getLenderToBookInstanceById(@RequestParam String id) {
        Optional<LenderToBookInstance> lenderToBookInstance = lenderToBookInstanceService.getLenderToBookInstanceById(id);

        // Check if the entity is present
        if (lenderToBookInstance.isPresent()) {
            LenderToBookInstanceResponse response = convertToLenderToBookInstanceResponse(lenderToBookInstance.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if not found
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<LenderToBookInstanceResponse>> getAllLenderToBookInstances() {
        List<LenderToBookInstance> lenderToBookInstances = lenderToBookInstanceService.getAllLenderToBookInstances();

        // Convert to response DTOs
        List<LenderToBookInstanceResponse> responseList = lenderToBookInstances.stream()
                .map(this::convertToLenderToBookInstanceResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LenderToBookInstanceResponse> updateLenderToBookInstance(
            @PathVariable("id") String id, @RequestBody LenderToBookInstanceRequest lenderToBookInstanceRequest) {
        LenderToBookInstance updated = lenderToBookInstanceService.updateLenderToBookInstance(id, lenderToBookInstanceRequest);

        LenderToBookInstanceResponse lenderToBookInstanceResponse = new LenderToBookInstanceResponse();
        lenderToBookInstanceResponse.setId(updated.getId());
        lenderToBookInstanceResponse.setLenderId(updated.getLender().getId());
        lenderToBookInstanceResponse.setBookInstanceId(updated.getBookInstance().getId());
        lenderToBookInstanceResponse.setName(updated.getName());
        lenderToBookInstanceResponse.setDescription(updated.getDescription());
        lenderToBookInstanceResponse.setLent(updated.getLent());
        lenderToBookInstanceResponse.setReturned(updated.getReturned());
        lenderToBookInstanceResponse.setDueBack(updated.getDueBack());
        return ResponseEntity.ok(lenderToBookInstanceResponse);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Void> deleteLenderToBookInstance(@RequestParam String id) {
        lenderToBookInstanceService.deleteLenderToBookInstance(id);
        return ResponseEntity.noContent().build();
    }


    private LenderToBookInstanceResponse convertToLenderToBookInstanceResponse(LenderToBookInstance lenderToBookInstance) {
        LenderToBookInstanceResponse response = new LenderToBookInstanceResponse();

        response.setId(lenderToBookInstance.getId());
        response.setName(lenderToBookInstance.getName());
        response.setDescription(lenderToBookInstance.getDescription());
        response.setLent(lenderToBookInstance.getLent());
        response.setDueBack(lenderToBookInstance.getDueBack());
        response.setReturned(lenderToBookInstance.getReturned());
        return response;
    }

}
