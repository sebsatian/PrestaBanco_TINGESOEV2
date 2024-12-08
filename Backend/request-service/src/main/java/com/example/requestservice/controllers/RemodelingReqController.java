package com.example.requestservice.controllers;

import com.example.requestservice.entities.RemodelingReqEntity;
import com.example.requestservice.models.SimulationEntity;
import com.example.requestservice.services.RemodelingReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/remodelingreq")
public class RemodelingReqController {
    @Autowired
    private RemodelingReqService remodelingReqService;


    @PostMapping("/create")
    public RemodelingReqEntity createRemodelingRequest(
            @RequestPart("simulation") SimulationEntity simulation,
            @RequestPart("incomeProof") MultipartFile incomeProof,
            @RequestPart("appraisalCertificate") MultipartFile appraisalCertificate,
            @RequestPart("savingsAccount") MultipartFile savingsAccount,
            @RequestPart("remodelingBudget") MultipartFile remodelingBudget,
            @RequestParam("monthlyIncome") BigDecimal monthlyIncome) {
        try {

            byte[] incomeProofBytes = incomeProof.getBytes();
            byte[] appraisalCertificateBytes = appraisalCertificate.getBytes();
            byte[] remodelingBudgetBytes = remodelingBudget.getBytes();
            byte[] savingsAccountBytes = savingsAccount.getBytes();


            return remodelingReqService.createRemodelingReq(
                    simulation,
                    incomeProofBytes,
                    appraisalCertificateBytes,
                    savingsAccountBytes,
                    remodelingBudgetBytes,
                    monthlyIncome);
        } catch (Exception e) {

            throw new RuntimeException("Error al procesar la solicitud");
        }
    }
}