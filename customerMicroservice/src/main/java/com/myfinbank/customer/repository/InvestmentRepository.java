package com.myfinbank.customer.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.customer.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByAccountId(Long accountId);
}

