package com.example.totalcost_service.controllers;

import com.example.totalcost_service.entities.TotalCostsEntity;
import com.example.totalcost_service.repositories.TotalCostsRepository;
import com.example.totalcost_service.services.TotalCostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/total-costs")
public class TotalCostsController {

    @Autowired
    private TotalCostsService totalCostsService;
    @Autowired
    private TotalCostsRepository totalCostsRepository;

    @GetMapping("/{requestId}")
    public TotalCostsEntity getTotalCosts(@PathVariable Long requestId) {
        System.out.println("Request ID......................................: " + requestId);
        return totalCostsRepository.getTotalCostsByRequestId(requestId);


    }

    @PostMapping("/create/{requestId}")
    public void createTotalCosts(@PathVariable Long requestId) {
       TotalCostsEntity totalCosts = totalCostsService.calculateTotalCosts(requestId);
         totalCostsRepository.save(totalCosts);

    }

}
