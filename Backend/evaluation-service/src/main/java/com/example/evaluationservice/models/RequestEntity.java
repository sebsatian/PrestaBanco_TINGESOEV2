package com.example.evaluationservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {
    private Long id;
    private BigDecimal annualInterestRate;
    private byte[] appraisalCertificate;
    private byte[] savingsAccount;
    private String clientRut;
    private LocalDateTime creationDate;
    private String currentStatus;
    private byte[] incomeProof;
    private BigDecimal loanAmount;
    private Integer loanType;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyPayment;
    private Integer years;
    // Will not be null only if the request is rejected or needs more information
    private String details;

}