package com.devshowcase.api.backend.repository;

import com.devshowcase.api.backend.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
            SELECT DISTINCT p
            FROM Project p
            LEFT JOIN p.technologies t
            WHERE (:technologyId IS NULL OR t.id = :technologyId)
            """)
    Page<Project> findByTechnology(@Param("technologyId") Long technologyId, Pageable pageable);
}