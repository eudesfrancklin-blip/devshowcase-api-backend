package com.devshowcase.api.backend.controller;

import com.devshowcase.api.backend.dto.request.FeedbackRequest;
import com.devshowcase.api.backend.dto.response.FeedbackResponse;
import com.devshowcase.api.backend.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{projectId}/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> create(
            @PathVariable Long projectId,
            @Valid @RequestBody FeedbackRequest request
    ) {
        FeedbackResponse response = feedbackService.create(projectId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}