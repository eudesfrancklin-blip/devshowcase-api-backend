package com.devshowcase.api.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class ProjectRequest {

    @NotBlank(message = "Título é obrigatório")
    private String title;

    @NotBlank(message = "Informe a descrição do projeto")
    private String description;

    @NotBlank(message = "O endereço do repositório deve ser informado")
    @Pattern(
            regexp = "https?://.+",
            message = "O endereço do repositório não é válido"
    )
    private String repositoryUrl;

    @Pattern(
            regexp = "https?://.+",
            message = "O endereço da demonstração não é válido"
    )
    private String demoUrl;

    @NotNull(message = "É necessário informar o perfil")
    private Long profileId;

    private List<Long> technologyIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(List<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}
