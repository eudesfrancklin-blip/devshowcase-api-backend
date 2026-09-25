package com.devshowcase.api.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProfileRequest {

    @NotBlank(message = "O nome deve ser informado")
    private String name;

    private String bio;

    @Pattern(
            regexp = "https?://.+",
            message = "Informe uma URL válida para o GitHub"
    )
    private String githubUrl;

    @Pattern(
            regexp = "https?://.+",
            message = "Informe uma URL válida para o LinkedIn"
    )
    private String linkedinUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }
}