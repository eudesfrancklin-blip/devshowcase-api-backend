package com.devshowcase.api.backend.repository;

import com.devshowcase.api.backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("""
            SELECT AVG(f.rating)
            FROM Feedback f
            WHERE f.project.id = :projectId
            """)
    Double calculateAverageRating(@Param("projectId") Long projectId);
}