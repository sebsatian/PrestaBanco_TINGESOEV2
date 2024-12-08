package com.example.evaluationservice.services;

import com.example.evaluationservice.EvaluationServiceApplication;
import com.example.evaluationservice.entities.EvaluationEntity;
import com.example.evaluationservice.models.RequestEntity;
import com.example.evaluationservice.repositories.EvaluationRepository;
import com.example.evaluationservice.models.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EvaluationService {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationServiceApplication evaluationServiceApplication;

    public EvaluationEntity createEvaluation(RequestEntity request,
                                             String creationSavingAccountDate,
                                             boolean jobStatus,
                                             BigDecimal balance,
                                             BigDecimal sumAllDeposits,
                                             BigDecimal balance12MonthsAgo,
                                             BigDecimal biggestWithdrawalLast12Months,
                                             BigDecimal balanceAfterBw12Months,
                                             BigDecimal biggestWithdrawalLast6Months,
                                             BigDecimal balanceAfterBw6Months,
                                             int numDepositsFirst4Months,
                                             int numDepositsLast4Months,
                                             int numDepositsSecond4Months,
                                             boolean creditHistory,
                                             BigDecimal sumAllDebts){
        // Verify if the request exists
        int requestId = Math.toIntExact(request.getId());
        // Verify if the evaluation already exists
        Optional<EvaluationEntity> evaluationOpt = getByRequestId(request.getId());

        EvaluationEntity evaluation;
        if (evaluationOpt.isPresent()) {
            // If exists, replace "evaluation" with the existing evaluation
            evaluation = evaluationOpt.get();
        } else {
            // If not, create a new evaluation
            evaluation = new EvaluationEntity();
        }

        LocalDate creationDate = LocalDate.parse(creationSavingAccountDate);

        evaluation.setRequestId(requestId);
        evaluation.setMonthlySalary(request.getMonthlyIncome());
        evaluation.setBalance(balance);
        evaluation.setBiggestWithdrawalLast12Months(biggestWithdrawalLast12Months);
        evaluation.setBiggestWithdrawalLast6Months(biggestWithdrawalLast6Months);
        evaluation.setBalance12MonthsAgo(balance12MonthsAgo);
        evaluation.setBalanceAfterBW12Months(balanceAfterBw12Months);
        evaluation.setBalanceAfterBW6Months(balanceAfterBw6Months);
        evaluation.setCreationSavingAccountDate(creationDate);
        evaluation.setCreditHistory(creditHistory);
        evaluation.setJobStatus(jobStatus);
        evaluation.setNumDepositsFirst4Months(numDepositsFirst4Months);
        evaluation.setNumDepositsLast4Months(numDepositsLast4Months);
        evaluation.setNumDepositsSecond4Months(numDepositsSecond4Months);
        evaluation.setSumAllDebts(sumAllDebts);
        evaluation.setSumAllDeposits(sumAllDeposits);

        calculateEvaluation(evaluation);

        return evaluation;
    }


    // Function to calculate the evaluation
    public void calculateEvaluation(EvaluationEntity evaluation) {

        // Find request by id
        RequestEntity request = getRequestById((long)evaluation.getRequestId());
        if (request == null) {
            throw new IllegalArgumentException("Request not found with the provided ID");
        }
        // Obtain the monthly payment
        BigDecimal monthlyPayment = request.getMonthlyPayment();
        // Calculate the cost to income ratio

        // (monthlyPayment / monthlySalary) * 100
        BigDecimal costToIncomeRatioCalc = monthlyPayment.divide(evaluation.getMonthlySalary(), 2, BigDecimal.ROUND_HALF_UP);
        costToIncomeRatioCalc = costToIncomeRatioCalc.multiply(BigDecimal.valueOf(100));

        if (costToIncomeRatioCalc.compareTo(BigDecimal.valueOf(35)) > 0) {
            // If the cost to income ratio is greater than 35%, set the cost to income ratio to false
            evaluation.setCostToIncomeRatio(false);
        } else {
            evaluation.setCostToIncomeRatio(true);
        }

        // Calculate the debt to income
        // (sumAllDebts > 50% of MonthlySalary ? false : true)
        BigDecimal fiftyPercentOfIncome = BigDecimal.valueOf(0.5).multiply(evaluation.getMonthlySalary());
        if (evaluation.getSumAllDebts().compareTo(fiftyPercentOfIncome) > 0) {
            evaluation.setDebtToIncomeRatio(false);
        } else {
            evaluation.setDebtToIncomeRatio(true);
        }

        // Calculate the in_age
        // Get the client's age
        ClientEntity client = getClientByRut(request.getClientRut());
        if (client == null) {
            throw new IllegalArgumentException("Client not found with the provided RUT");
        }
        LocalDate dob;
        double age = 0;
        // Calculate the exact age of the client
        if ( (dob = client.getBirthDate()) != null) {
            age = LocalDate.now().getYear() - dob.getYear();
            if (LocalDate.now().getDayOfYear() < dob.getDayOfYear()) {
                age--;
            }
        }
        // If the client is older than 70, set in_age to false
        if (age > 70) {
            evaluation.setInAge(false);
        } else {
            evaluation.setInAge(true);
        }

        // Calculate the minimum balance that the client must have
        BigDecimal loanAmount = request.getLoanAmount();
        BigDecimal minimumBalance = loanAmount.multiply(BigDecimal.valueOf(0.1));
        evaluation.setMinimumBalance(minimumBalance);

    }

    public RequestEntity getRequestById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/requests/" + id, RequestEntity.class);
    }

    public ClientEntity getClientByRut(String rut) {
        try {
            return restTemplate.getForObject("http://localhost:8080/client/getByRut/" + rut, ClientEntity.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Manejar errores HTTP específicos
            System.err.println("Error al obtener el cliente: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            System.err.println("Error inesperado al obtener el cliente: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el cliente: " + e.getMessage());
        }
    }

    public Optional<EvaluationEntity> getByRequestId(Long requestId) {
        return evaluationRepository.findByRequestId(requestId);
    }

}
