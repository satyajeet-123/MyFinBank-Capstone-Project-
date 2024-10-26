package com.myfinbank.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.customer.dto.AccountDTO;
import com.myfinbank.customer.dto.CustomerAccountDTO;
import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.AccountRepository;
import com.myfinbank.customer.repository.CustomerRepository;

@Service
public class CustomersService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Activate a customer
    public CustomerAccountDTO activateCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setActive(true); // Activate the customer
            customerRepository.save(customer);
            return convertToDTO(customer);
        }
        return null; // Customer not found
    }

    // Deactivate a customer
    public CustomerAccountDTO deactivateCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setActive(false); // Deactivate the customer
            customerRepository.save(customer);
            return convertToDTO(customer);
        }
        return null; // Customer not found
    }

    // Get all customers
    public List<CustomerAccountDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a customer by ID
    public CustomerAccountDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(this::convertToDTO).orElse(null);
    }

    // Get all accounts for a specific customer
    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts.stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList());
    }
    
 // Get all accounts
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll(); // Retrieve all accounts from the repository
        return accounts.stream()
                       .map(this::convertToAccountDTO) // Convert each Account entity to AccountDTO
                       .collect(Collectors.toList());
    }

    // Create a new account for a specific customer
    public AccountDTO createAccount(Long customerId, AccountDTO accountDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Account account = new Account();
            account.setAccountNumber(accountDTO.getAccountNumber());
            account.setBalance(accountDTO.getBalance());
            account.setCustomer(optionalCustomer.get());
            Account savedAccount = accountRepository.save(account);
            return convertToAccountDTO(savedAccount);
        }
        return null; // Customer not found
    }

    // Update an existing account
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountNumber(accountDTO.getAccountNumber());
            account.setBalance(accountDTO.getBalance());
            Account updatedAccount = accountRepository.save(account);
            return convertToAccountDTO(updatedAccount);
        }
        return null; // Account not found
    }

    // Delete an account
    public boolean deleteAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return true; // Account deleted
        }
        return false; // Account not found
    }

    // Convert Customer to DTO
    private CustomerAccountDTO convertToDTO(Customer customer) {
        List<AccountDTO> accountDTOs = customer.getAccounts().stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList());
        return new CustomerAccountDTO(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.isActive(),
                accountDTOs
        );
    }

    // Convert Account to DTO
    private AccountDTO convertToAccountDTO(Account account) {
        return new AccountDTO(account.getId(), account.getAccountNumber(), account.getBalance());
    }
}
