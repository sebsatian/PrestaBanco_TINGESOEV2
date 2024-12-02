package com.example.requestservice.repositories;

import com.example.requestservice.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
    List<RequestEntity> findByClientRut(String rut);
}