package com.example.trackingservice.repositories;

import com.example.trackingservice.entities.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {

    List<TrackingEntity> getByClientRut(String clientRut);

    TrackingEntity getTrackingByRequestId(Long request_id);

}
