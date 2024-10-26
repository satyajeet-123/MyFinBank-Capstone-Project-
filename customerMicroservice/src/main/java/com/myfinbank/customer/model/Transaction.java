package com.myfinbank.customer.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String transactionId;
    
    private Double amount;
    
    private String type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    //Constructor
    public Transaction() {
        this.date = LocalDateTime.now();
    }
}
