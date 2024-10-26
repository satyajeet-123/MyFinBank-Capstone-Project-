package com.myfinbank.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.model.Transaction;
import com.myfinbank.customer.repository.AccountRepository;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.repository.TransactionRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	// (used in AdminController)
	public Account createAccount(Account account) {
		Customer customer = customerRepository.findById(account.getCustomer().getId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		account.setCustomer(customer);
		return accountRepository.save(account);
	}

	// (used in AdminController)
	public Optional<Account> findAccountById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	public Account deposit(Account account, Double amount) {
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);

		Transaction transaction = new Transaction();
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setAmount(amount);
		transaction.setType("DEPOSIT");
		transaction.setAccount(account);
		transactionRepository.save(transaction);

		return account;
	}
	

	public Account withdraw(Account account, Double amount) {
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);

		Transaction transaction = new Transaction();
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setAmount(amount);
		transaction.setType("WITHDRAWAL");
		transaction.setAccount(account);
		transactionRepository.save(transaction);

		return account;
	}

	public Transaction transfer(Account fromAccount, Account toAccount, Double amount) {
		if (fromAccount.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		accountRepository.save(fromAccount);

		toAccount.setBalance(toAccount.getBalance() + amount);
		accountRepository.save(toAccount);

		Transaction transaction = new Transaction();
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setAmount(amount);
		transaction.setType("TRANSFER");
		transaction.setAccount(fromAccount);
		transactionRepository.save(transaction);

		return transaction;
	}

	// (used in AdminController)
	public List<Account> getAccountsByCustomerId(Long customerId) {
		return accountRepository.findByCustomerId(customerId);
	}

	
//	public List<Transaction> getTransactionsHistory(Long accountId) {
//		return transactionRepository.findByAccountId(accountId);
//	}
	
	public List<Transaction> getTransactionsHistory(Long accountId) {
        // Check if the account exists
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        // Retrieve transactions for the account
        return transactionRepository.findByAccount(account);
    }
	

	// Find all accounts (used in AdminController)
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	// Update an account (used in AdminController)
	public Account updateAccount(Account account) {
		Optional<Account> existingAccount = accountRepository.findById(account.getId());
		if (existingAccount.isPresent()) {
			account.setAccountNumber(existingAccount.get().getAccountNumber());
			account.setCustomer(existingAccount.get().getCustomer());
	        account.setBalance(existingAccount.get().getBalance());
			return accountRepository.save(account);
		} else {
			throw new RuntimeException("Account not found");
		}
	}

	// Delete an account by ID (used in AdminController)
	public void deleteAccount(Long accountId) {
		accountRepository.deleteById(accountId);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
//        List<Account> accounts = accountRepository.findByCustomerId(customerId);
//        return accounts.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

//    public AccountDTO createAccount(AccountDTO accountDTO) {
//        Account account = convertToEntity(accountDTO);
//        account = accountRepository.save(account);
//        return convertToDTO(account);
//    }
//
//    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
//        Account account = accountRepository.findById(accountId).orElseThrow();
//        account.setAccountNumber(accountDTO.getAccountNumber());
//        account.setBalance(accountDTO.getBalance());
//        account = accountRepository.save(account);
//        return convertToDTO(account);
//    }
//
//    public void deleteAccount(Long accountId) {
//        accountRepository.deleteById(accountId);
//    }

    
    
}
