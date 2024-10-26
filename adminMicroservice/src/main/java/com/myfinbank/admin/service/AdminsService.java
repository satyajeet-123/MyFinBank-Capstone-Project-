package com.myfinbank.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myfinbank.admin.client.CustomerFeignClient;
import com.myfinbank.admin.dto.AccountDTO;
import com.myfinbank.admin.dto.CustomerAccountDTO;

@Service
public class AdminsService {

    @Autowired
    private CustomerFeignClient customerFeignClient;

    public CustomerAccountDTO activateCustomer(Long id) {
        ResponseEntity<CustomerAccountDTO> response = customerFeignClient.activateCustomer(id);
        return response.getBody(); // Handle the response appropriately
    }

    public CustomerAccountDTO deactivateCustomer(Long id) {
        ResponseEntity<CustomerAccountDTO> response = customerFeignClient.deactivateCustomer(id);
        return response.getBody(); // Handle the response appropriately
    }

    public List<CustomerAccountDTO> getAllCustomers() {
        ResponseEntity<List<CustomerAccountDTO>> response = customerFeignClient.getAllCustomers();
        return response.getBody(); // Handle the response appropriately
    }

    public CustomerAccountDTO getCustomerById(Long id) {
        ResponseEntity<CustomerAccountDTO> response = customerFeignClient.getCustomerById(id);
        return response.getBody(); // Handle the response appropriately
    }

    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        ResponseEntity<List<AccountDTO>> response = customerFeignClient.getAccountsByCustomerId(customerId);
        return response.getBody(); // Handle the response appropriately
    }
    
    
    
    // Accounts 
    public List<AccountDTO> getAllAccounts() {
        return customerFeignClient.getAllAccounts();
    }

    public AccountDTO createAccount(Long customerId, AccountDTO accountDTO) {
        ResponseEntity<AccountDTO> response = customerFeignClient.createAccount(customerId, accountDTO);
        return response.getBody(); // Handle the response appropriately
    }

    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        ResponseEntity<AccountDTO> response = customerFeignClient.updateAccount(accountId, accountDTO);
        return response.getBody(); // Handle the response appropriately
    }

    public boolean deleteAccount(Long accountId) {
        ResponseEntity<Void> response = customerFeignClient.deleteAccount(accountId);
        return response.getStatusCode().is2xxSuccessful(); // Check if deletion was successful
    }
}
