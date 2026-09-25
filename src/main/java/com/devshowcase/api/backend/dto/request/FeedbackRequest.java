package com.devshowcase.api.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackRequest {

    @NotNull(message = "Informe a nota da avaliação")
    @Min(value = 1, message = "A nota mínima permitida é 1")
    @Max(value = 5, message = "A nota máxima permitida é 5")
    private Integer rating;

    @Size(max = 500, message = "O comentário pode ter até 500 caracteres")
    private String comment;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
