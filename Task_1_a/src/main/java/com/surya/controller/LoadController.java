package com.surya.controller;

import com.surya.dto.LoadRequest;
import com.surya.model.Load;
import com.surya.service.LoadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/loads")
@RequiredArgsConstructor
public class LoadController {
    private final LoadService loadService;

    @PostMapping
    public ResponseEntity<Load> createLoad(@Valid @RequestBody LoadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loadService.createLoad(request));
    }

    @GetMapping
    public ResponseEntity<Page<Load>> getAllLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType,
            Pageable pageable) {
        return ResponseEntity.ok(loadService.getFilteredLoads(shipperId, truckType, pageable));
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getLoadById(loadId));
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(
            @PathVariable UUID loadId,
            @Valid @RequestBody LoadRequest request) {
        return ResponseEntity.ok(loadService.updateLoad(loadId, request));
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}