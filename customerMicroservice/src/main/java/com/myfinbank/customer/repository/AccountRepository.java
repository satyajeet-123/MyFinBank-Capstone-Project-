package com.myfinbank.customer.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.customer.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
    Optional <Account> findByAccountNumber(String AccountNumber);
}
