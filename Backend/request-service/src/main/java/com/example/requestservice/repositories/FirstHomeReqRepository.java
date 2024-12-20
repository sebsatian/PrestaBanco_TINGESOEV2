package com.example.requestservice.repositories;

import com.example.requestservice.entities.FirstHomeReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstHomeReqRepository extends JpaRepository<FirstHomeReqEntity, Long> {
}