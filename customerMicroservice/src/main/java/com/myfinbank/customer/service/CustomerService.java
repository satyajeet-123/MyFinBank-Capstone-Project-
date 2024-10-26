package com.myfinbank.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinbank.customer.dto.AccountDTO;
import com.myfinbank.customer.dto.CustomerAccountDTO;
import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
//
//	@Autowired
//    private PasswordEncoder passwordEncoder;

//    // Customer Registration
//    public Customer register(Customer customer) {
//        // Check if the username already exists
//        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
//            throw new RuntimeException("Username already exists");
//        }
//        // Encrypt the password
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        // Save the customer to the repository
//        return customerRepository.save(customer);
//    }

	// Customer Registration
	@Transactional
	public Customer register(Customer customer) {
	    // Check if the username already exists
	    if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
	        throw new RuntimeException("Username already exists");
	    }
	    // Save the customer to the repository with plain text password
	    return customerRepository.save(customer);
	}
		
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findByCustomerId(Long id) {
		return customerRepository.findById(id);
	}

	public Optional<Customer> findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

	public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

	public Customer deactivateCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		customer.setActive(false);
		return customerRepository.save(customer);
	}

	public Customer activateCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		customer.setActive(true);
		return customerRepository.save(customer);
	}
	
	public List<CustomerAccountDTO> getCustomerAccounts() {
        List<CustomerAccountDTO> customerAccounts = new ArrayList<>();

        // Fetch all customers with their accounts
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            List<AccountDTO> accountDTOs = customer.getAccounts().stream()
                .map(account -> new AccountDTO(account.getId(), account.getAccountNumber(), account.getBalance()))
                .collect(Collectors.toList());
            
            CustomerAccountDTO dto = new CustomerAccountDTO(customer.getId(), customer.getUsername(), customer.getEmail(), customer.isActive(), accountDTOs);
            customerAccounts.add(dto);
        }

        return customerAccounts;
    }

}
