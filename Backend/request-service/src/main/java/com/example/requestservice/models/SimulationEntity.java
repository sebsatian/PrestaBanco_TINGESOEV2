package com.example.requestservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationEntity {
    private Long id; // This is the primary key
    private int clientId;
    private int loanType;
    private BigDecimal propertyValue;
    private BigDecimal loanAmount;
    private float annualInterestRate;
    private int years; // Plazo en años
    private int numberOfPayments;
    private BigDecimal monthlyPayment;
    private float percentage;
    private BigDecimal finalAmount;

}
