package com.devshowcase.api.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.devshowcase.api.backend.dto.request.ProjectRequest;
import com.devshowcase.api.backend.dto.response.ProjectResponse;
import com.devshowcase.api.backend.entity.Profile;
import com.devshowcase.api.backend.entity.Project;
import com.devshowcase.api.backend.entity.Technology;
import com.devshowcase.api.backend.exception.ResourceNotFoundException;
import com.devshowcase.api.backend.repository.ProfileRepository;
import com.devshowcase.api.backend.repository.ProjectRepository;
import com.devshowcase.api.backend.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(ProjectRepository projectRepository, ProfileRepository profileRepository, TechnologyRepository technologyRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    @Transactional
    public ProjectResponse create(ProjectRequest request) {

        Profile profile = profileRepository.findById(request.getProfileId()).orElseThrow(() -> new ResourceNotFoundException("Profile não encontrado"));

        List<Technology> technologies = request.getTechnologyIds() == null ? List.of() : technologyRepository.findAllById(request.getTechnologyIds());

        if (request.getTechnologyIds() != null && technologies.size() != request.getTechnologyIds().size()) {

            throw new ResourceNotFoundException("Uma ou mais tecnologias não foram encontradas");
        }

        Project project = new Project();

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setRepositoryUrl(request.getRepositoryUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setProfile(profile);
        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll() {

        return projectRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponse> findAll(Long technologyId, int page, int size) {

        Page<Project> projects = projectRepository.findByTechnology(technologyId, PageRequest.of(page, size));

        return projects.map(this::toResponse);
    }

    @Transactional
    public ProjectResponse upvote(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        project.setUpvotes(project.getUpvotes() + 1);

        Project updatedProject = projectRepository.save(project);

        return toResponse(updatedProject);
    }

    private ProjectResponse toResponse(Project project) {

        List<Long> technologyIds = project.getTechnologies().stream().map(Technology::getId).toList();

        return new ProjectResponse(project.getId(), project.getTitle(), project.getDescription(), project.getRepositoryUrl(), project.getDemoUrl(), project.getUpvotes(), project.getRatingAverage(), project.getProfile().getId(), technologyIds);
    }
}