package com.devshowcase.api.backend.controller;

import com.devshowcase.api.backend.dto.request.ProjectRequest;
import com.devshowcase.api.backend.dto.response.ProjectResponse;
import com.devshowcase.api.backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody ProjectRequest request
    ) {
        ProjectResponse response = projectService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(
            @RequestParam(required = false) Long technologyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProjectResponse> response = projectService.findAll(
                technologyId,
                page,
                size
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponse> upvote(
            @PathVariable Long id
    ) {
        ProjectResponse response = projectService.upvote(id);

        return ResponseEntity.ok(response);
    }
}