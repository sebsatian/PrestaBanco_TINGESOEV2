package com.example.evaluationservice.repositories;

import com.example.evaluationservice.entities.SavingCapacityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCapacityRepository extends JpaRepository<SavingCapacityEntity, Long> {

    SavingCapacityEntity findByRequestId(int requestId);
}
