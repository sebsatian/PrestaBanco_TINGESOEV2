package com.example.trackingservice.controllers;


import com.example.trackingservice.entities.TrackingEntity;
import com.example.trackingservice.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracking")
@CrossOrigin("*")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;


    @PostMapping("/update-status/{requestId}")
    public void updateStatusByRequestId(Long requestId, @RequestParam String status) {
        trackingService.updateTrackingStatus(requestId, status);
    }

    @GetMapping("/get-all/{rut}")
    public String getByClientRut(String rut) {
        List<TrackingEntity> trackings = trackingService.getByClientRut(rut);
        return trackings.toString();
    }



}
