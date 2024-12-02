package com.example.requestservice.repositories;


import com.example.requestservice.entities.BusinessReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessReqRepository extends JpaRepository<BusinessReqEntity, Long> {
}
