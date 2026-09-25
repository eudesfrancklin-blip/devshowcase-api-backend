package com.devshowcase.api.backend.dto.response;

public class TechnologyResponse {

    private Long id;
    private String name;

    public TechnologyResponse() {
    }

    public TechnologyResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}