package com.devshowcase.api.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequest {

    @NotBlank(message = "Informe o nome da tecnologia")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}