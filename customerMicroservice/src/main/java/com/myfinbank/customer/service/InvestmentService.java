package com.myfinbank.customer.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Investment;
import com.myfinbank.customer.repository.AccountRepository;
import com.myfinbank.customer.repository.InvestmentRepository;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Investment invest(Long accountId, String investmentType, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Investment investment = new Investment();
        investment.setAccount(account);
        investment.setInvestmentType(investmentType);
        investment.setAmount(amount);
        investment.setInvestmentDate(LocalDateTime.now());

        return investmentRepository.save(investment);
    }

    public List<Investment> getInvestmentsByAccountId(Long accountId) {
        return investmentRepository.findByAccountId(accountId);
    }
    
}

