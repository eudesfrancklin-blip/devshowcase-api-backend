package com.devshowcase.api.backend.dto.response;

public class ProfileResponse {

    private Long id;
    private String name;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;

    public ProfileResponse() {
    }

    public ProfileResponse(
            Long id,
            String name,
            String bio,
            String githubUrl,
            String linkedinUrl
    ) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }
}