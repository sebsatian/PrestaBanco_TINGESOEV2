package com.example.requestservice.repositories;

import com.example.requestservice.entities.SecondHomeReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondHomeReqRepository extends JpaRepository<SecondHomeReqEntity, Long> {
}
