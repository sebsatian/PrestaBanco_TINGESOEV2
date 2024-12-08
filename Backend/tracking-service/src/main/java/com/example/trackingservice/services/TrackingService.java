package com.example.trackingservice.services;

import com.example.trackingservice.entities.TrackingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.trackingservice.repositories.TrackingRepository;

import java.util.List;

@Service
public class TrackingService {

    @Autowired
    TrackingRepository trackingRepository;

    public TrackingEntity getTrackingByRequestId(Long request_id) {
        return trackingRepository.getTrackingByRequestId(request_id);
    }

    public List<TrackingEntity> getByClientRut(String clientRut) {
        return trackingRepository.getByClientRut(clientRut);
    }

    public void updateTrackingStatus(Long request_id, String status) {
        TrackingEntity tracking = getTrackingByRequestId(request_id);
        tracking.setStatus(status);
        trackingRepository.save(tracking);
    }

    public void createTracking(Long requestId, String clientRut, String type) {
        TrackingEntity tracking = new TrackingEntity();
        tracking.setRequestId(requestId);
        tracking.setType(type);
        tracking.setClientRut(clientRut);
        tracking.setStatus("En Revision Inicial");
        trackingRepository.save(tracking);
    }
}
