package com.devshowcase.api.backend.dto.response;

import java.util.List;

public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private String repositoryUrl;
    private String demoUrl;
    private Integer upvotes;
    private Double ratingAverage;
    private Long profileId;
    private List<Long> technologyIds;

    public ProjectResponse() {
    }

    public ProjectResponse(
            Long id,
            String title,
            String description,
            String repositoryUrl,
            String demoUrl,
            Integer upvotes,
            Double ratingAverage,
            Long profileId,
            List<Long> technologyIds
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.demoUrl = demoUrl;
        this.upvotes = upvotes;
        this.ratingAverage = ratingAverage;
        this.profileId = profileId;
        this.technologyIds = technologyIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public Long getProfileId() {
        return profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }
}