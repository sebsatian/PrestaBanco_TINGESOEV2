package com.example.requestservice.services;

import com.example.requestservice.entities.RemodelingReqEntity;
import com.example.requestservice.models.SimulationEntity;
import com.example.requestservice.repositories.RemodelingReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RemodelingReqService {
    @Autowired
    private RemodelingReqRepository remodelingReqRepository;
    @Autowired
    private RequestService requestService;

    public RemodelingReqEntity createRemodelingReq(SimulationEntity simulation,
                                                   byte[] incomeProof,
                                                   byte[] appraisalCertificate,
                                                   byte[] savingsAccount,
                                                   byte[] remodelingBudget,
                                                   BigDecimal monthlyIncome) {
        RemodelingReqEntity remodelingReq = new RemodelingReqEntity();
        int clientId = simulation.getClientId();
        Long id = (long) clientId;
        String rut = requestService.getClientRutById(id);
        remodelingReq.setClientRut(rut);
        remodelingReq.setLoanType(4);
        remodelingReq.setIncomeProof(incomeProof);
        remodelingReq.setSavingsAccount(savingsAccount);
        remodelingReq.setAppraisalCertificate(appraisalCertificate);
        remodelingReq.setRemodelingBudget(remodelingBudget);
        remodelingReq.setYears(simulation.getYears());
        remodelingReq.setLoanAmount(simulation.getLoanAmount());
        remodelingReq.setAnnualInterestRate(BigDecimal.valueOf(simulation.getAnnualInterestRate()));
        remodelingReq.setMonthlyIncome(monthlyIncome);
        remodelingReq.setMonthlyPayment(simulation.getMonthlyPayment()); // Set the monthly payment
        remodelingReq.setCurrentStatus("En revisión inicial");
        remodelingReq.setCreationDate(java.time.LocalDateTime.now());

        return remodelingReqRepository.save(remodelingReq);
    }
}