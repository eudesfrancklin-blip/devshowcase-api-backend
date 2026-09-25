package com.devshowcase.api.backend.controller;

import com.devshowcase.api.backend.dto.request.TechnologyRequest;
import com.devshowcase.api.backend.dto.response.TechnologyResponse;
import com.devshowcase.api.backend.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<TechnologyResponse> create(
            @Valid @RequestBody TechnologyRequest request
    ) {
        TechnologyResponse response = technologyService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponse>> findAll() {
        List<TechnologyResponse> response = technologyService.findAll();

        return ResponseEntity.ok(response);
    }
}
