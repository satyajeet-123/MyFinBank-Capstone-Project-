package com.myfinbank.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Loan;
import com.myfinbank.customer.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

//	  Emi - Calculator
//    public BigDecimal calculateEMI(BigDecimal loanAmount, BigDecimal annualInterestRate, int months) {
//        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP)
//                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
//
//        BigDecimal onePlusRPowerN = (BigDecimal.ONE.add(monthlyInterestRate)).pow(months);
//        BigDecimal numerator = loanAmount.multiply(monthlyInterestRate).multiply(onePlusRPowerN);
//        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);
//
//        return numerator.divide(denominator, RoundingMode.HALF_UP);
//    }
    
    public Loan applyForLoan(Account account, Double amount) {
        Loan loan = new Loan();
        loan.setAccount(account); // Set the account linked to this loan
        loan.setAmount(amount);
        loan.setStatus("PENDING"); // Set the initial status
        // Save loan to the database
        return loanRepository.save(loan);
    }

    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Check the account balance before approving
        Double accountBalance = loan.getAccount().getBalance(); // Assuming Account has a getBalance() method
        if (accountBalance >= loan.getAmount() * 0.2) { // Example: Approve only if balance is at least 20% of the loan amount
            loan.setStatus("APPROVED"); // Change status to APPROVED
        } else {
            throw new RuntimeException("Insufficient balance to approve loan."); // Throw error if balance is insufficient
        }

        return loanRepository.save(loan);
    }

    public Loan denyLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus("DENIED"); // Change status to DENIED
        return loanRepository.save(loan);
    }

    public List<Loan> findAllLoans() {
        return loanRepository.findAll(); // Retrieve all loans
    }

    public List<Loan> findLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId); // Retrieve loans by account ID
    }
}
