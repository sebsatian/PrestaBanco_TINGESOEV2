package com.example.trackingservice.controllers;


import com.example.trackingservice.entities.TrackingEntity;
import com.example.trackingservice.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @PostMapping("/update/{requestId}")
    public void updateStatusByRequestId(@PathVariable Long requestId, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        trackingService.updateTrackingStatus(requestId, status);
    }
    @GetMapping("/getByRut/{rut}")
    public List<TrackingEntity> getByClientRut(@PathVariable String rut) {
        return trackingService.getByClientRut(rut);
    }

    @PostMapping("/create")
    public void createTracking(@RequestBody TrackingEntity trackingRequest) {
        trackingService.createTracking(trackingRequest.getRequestId(), trackingRequest.getClientRut(), trackingRequest.getType());
    }



}
