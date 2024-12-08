package com.example.simulationservice.controllers;

import com.example.simulationservice.models.ClientEntity;
import com.example.simulationservice.entities.SimulationEntity;
import com.example.simulationservice.services.SimulationService;

import com.sun.jdi.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @PostMapping("/simulate")
    public ResponseEntity<?> createOrUpdateSimulation(@RequestBody Map<String, Object> simulationData) {
        try {
            System.out.println("Received simulation data: " + simulationData);

            // Validate the request body
            if (!simulationData.containsKey("rut") || !simulationData.containsKey("propertyValue") ||
                    !simulationData.containsKey("loanType") || !simulationData.containsKey("years") ||
                    !simulationData.containsKey("percentage")) {
                return ResponseEntity.badRequest().body("All fields are required");
            }

            // Extract the simulation data
            String rut = (String) simulationData.get("rut");
            if (rut == null || rut.isEmpty()) {
                return ResponseEntity.badRequest().body("RUT is required");
            }
            System.out.println("RUT: " + rut);

            Number propertyValueNum = (Number) simulationData.get("propertyValue");
            if (propertyValueNum == null) {
                return ResponseEntity.badRequest().body("Property value is required");
            }
            int propertyValue = propertyValueNum.intValue();
            System.out.println("Property Value: " + propertyValue);

            Number loanTypeNum = (Number) simulationData.get("loanType");
            if (loanTypeNum == null) {
                return ResponseEntity.badRequest().body("Loan type is required");
            }
            Long loanType = loanTypeNum.longValue();
            System.out.println("Loan Type: " + loanType);

            Number yearsNum = (Number) simulationData.get("years");
            if (yearsNum == null) {
                return ResponseEntity.badRequest().body("Years are required");
            }
            int years = yearsNum.intValue();
            System.out.println("Years: " + years);

            Number percentageNum = (Number) simulationData.get("percentage");
            if (percentageNum == null) {
                return ResponseEntity.badRequest().body("Percentage is required");
            }
            float percentage = percentageNum.floatValue();
            System.out.println("Percentage: " + percentage);

            // Check if the user already has a simulation
            ClientEntity client = simulationService.getClientByRut(rut);
            if (client == null) {
                return ResponseEntity.status(404).body("Client not found with the provided RUT");
            }

            Optional<SimulationEntity> existingSimulationOpt = simulationService.findByClientId(client.getId());
            SimulationEntity simulation;
            if (existingSimulationOpt.isPresent()) {
                // Update the existing simulation
                simulation = simulationService.updateSimulation(existingSimulationOpt.get().getId(), rut, propertyValue, loanType, years, percentage);
            } else {
                // Create a new simulation
                simulation = simulationService.createSimulation(rut, propertyValue, loanType, years, percentage);
            }

            return ResponseEntity.ok(simulation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @GetMapping("/simulate/{simulationId}")
    public ResponseEntity<?> getSimulationById(@PathVariable Long simulationId) {
        try {
            SimulationEntity simulation = simulationService.getSimulationEntityById(simulationId);
            // Create a response map with the simulation and the client's RUT, in Long
            Long clientId = (long) simulation.getClientId();
            System.out.println("Client ID: " + clientId);
            ClientEntity client = simulationService.getClientById(clientId);
            String rut = client.getRut();

            Map<String, Object> response = new HashMap<>();
            response.put("simulation", simulation);
            response.put("rut", rut);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @PutMapping("/change/{simulationId}")
    public ResponseEntity<?> updateSimulation(@PathVariable Long simulationId, @RequestBody Map<String, Object> simulationData) {
        try {
            // Validate the request body
            if (!simulationData.containsKey("propertyValue") ||
                    !simulationData.containsKey("loanType") || !simulationData.containsKey("years") ||
                    !simulationData.containsKey("percentage")) {
                return ResponseEntity.badRequest().body("All fields are required");
            }

            // Extract the simulation data
            Number propertyValueNum = (Number) simulationData.get("propertyValue");
            if (propertyValueNum == null) {
                return ResponseEntity.badRequest().body("Property value is required");
            }
            int propertyValue = propertyValueNum.intValue();
            System.out.println("Property Value: " + propertyValue);

            Number loanTypeNum = (Number) simulationData.get("loanType");
            if (loanTypeNum == null) {
                return ResponseEntity.badRequest().body("Loan type is required");
            }
            Long loanType = loanTypeNum.longValue();
            System.out.println("Loan Type: " + loanType);

            Number yearsNum = (Number) simulationData.get("years");
            if (yearsNum == null) {
                return ResponseEntity.badRequest().body("Years are required");
            }
            int years = yearsNum.intValue();
            System.out.println("Years: " + years);

            Number percentageNum = (Number) simulationData.get("percentage");
            if (percentageNum == null) {
                return ResponseEntity.badRequest().body("Percentage is required");
            }
            float percentage = percentageNum.floatValue();
            System.out.println("Percentage: " + percentage);

            // Find the existing simulation by ID
            SimulationEntity existingSimulation = simulationService.findByClientId(simulationId)
                    .orElseThrow(() -> new IllegalArgumentException("Simulation not found with the provided ID"));

            // Obtain the client's RUT using the simulation's client ID
            String rut = simulationService.getRutByClientId((long) existingSimulation.getClientId());

            // Call the service to update the simulation
            SimulationEntity updatedSimulationEntity = simulationService.updateSimulation(
                    simulationId,
                    rut,
                    propertyValue,
                    loanType,
                    years,
                    percentage
            );

            return ResponseEntity.ok(updatedSimulationEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }


}