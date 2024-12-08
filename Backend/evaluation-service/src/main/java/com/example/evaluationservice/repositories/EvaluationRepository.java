package com.example.evaluationservice.repositories;

import com.example.evaluationservice.entities.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {
    Optional<EvaluationEntity> findByRequestId(Long requestId);

}
