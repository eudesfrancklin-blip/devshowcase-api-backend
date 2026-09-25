package com.devshowcase.api.backend.service;

import com.devshowcase.api.backend.dto.request.TechnologyRequest;
import com.devshowcase.api.backend.dto.response.TechnologyResponse;
import com.devshowcase.api.backend.entity.Technology;
import com.devshowcase.api.backend.exception.DuplicateResourceException;
import com.devshowcase.api.backend.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Transactional
    public TechnologyResponse create(TechnologyRequest request) {

        technologyRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(technology -> {
                    throw new DuplicateResourceException(
                            "Tecnologia já cadastrada"
                    );
                });

        Technology technology = new Technology();

        technology.setName(request.getName());

        Technology savedTechnology = technologyRepository.save(technology);

        return new TechnologyResponse(
                savedTechnology.getId(),
                savedTechnology.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<TechnologyResponse> findAll() {

        return technologyRepository.findAll()
                .stream()
                .map(technology -> new TechnologyResponse(
                        technology.getId(),
                        technology.getName()
                ))
                .toList();
    }
}