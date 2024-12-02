package com.example.evaluationservice.controllers;

import com.example.evaluationservice.entities.SavingCapacityEntity;
import com.example.evaluationservice.repositories.SavingCapacityRepository;
import com.example.evaluationservice.services.SavingCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saving-capacity")
@CrossOrigin("*")
public class SavingCapacityController {

    @Autowired
    private SavingCapacityService savingCapacityService;

    @Autowired
    private SavingCapacityRepository savingCapacityRepository;

    @GetMapping("/{requestId}")
    public SavingCapacityEntity getSavingCapacity(@PathVariable int requestId) {
        return savingCapacityRepository.findByRequestId(requestId);
    }


}
