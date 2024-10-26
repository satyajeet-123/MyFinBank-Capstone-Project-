package com.myfinbank.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByAccount(Account account);

}
