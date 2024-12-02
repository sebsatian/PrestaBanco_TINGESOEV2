package com.example.requestservice.services;

import com.example.requestservice.entities.FirstHomeReqEntity;
import com.example.requestservice.models.SimulationEntity;
import com.example.requestservice.repositories.FirstHomeReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FirstHomeReqService {

    @Autowired
    private FirstHomeReqRepository firstHomeReqRepository;
    @Autowired
    private RequestService requestService;

    public FirstHomeReqEntity createFirstHomeRequest(SimulationEntity simulation,
                                                     byte[] appraisalCertificate,
                                                     byte[] incomeProof,
                                                     byte[] savingsAccountBytes,
                                                     byte[] jobContract,
                                                     byte[] creditHistory,
                                                     BigDecimal monthlyIncome) {
        int loanType = 1;
        String rut = requestService.getClientRutById((long) simulation.getClientId());
        FirstHomeReqEntity firstHomeRequest = new FirstHomeReqEntity();
        firstHomeRequest.setClientRut(rut);
        firstHomeRequest.setLoanType(loanType);
        firstHomeRequest.setCreditHistory(creditHistory);
        firstHomeRequest.setJobContract(jobContract);
        firstHomeRequest.setIncomeProof(incomeProof);
        firstHomeRequest.setAppraisalCertificate(appraisalCertificate);
        firstHomeRequest.setSavingsAccount(savingsAccountBytes);
        firstHomeRequest.setCreationDate(java.time.LocalDateTime.now());
        firstHomeRequest.setLoanAmount(simulation.getLoanAmount());
        firstHomeRequest.setYears(simulation.getYears());
        firstHomeRequest.setCurrentStatus("En revisión inicial");
        firstHomeRequest.setAnnualInterestRate(BigDecimal.valueOf(simulation.getAnnualInterestRate()));
        firstHomeRequest.setMonthlyPayment(simulation.getMonthlyPayment());
        firstHomeRequest.setMonthlyIncome(monthlyIncome);

        return firstHomeReqRepository.save(firstHomeRequest);



    }
}