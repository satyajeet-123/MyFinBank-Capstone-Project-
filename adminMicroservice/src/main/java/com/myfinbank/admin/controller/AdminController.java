package com.myfinbank.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myfinbank.admin.dto.AccountDTO;
import com.myfinbank.admin.dto.CustomerAccountDTO;
import com.myfinbank.admin.service.AdminsService;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminsService adminService;

	// Get all customers
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		List<CustomerAccountDTO> customers = adminService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "customerList"; // Return the JSP page name to display the list of customers
	}

	// Activate a customer from customerList
	@PostMapping("/customers/activate/{id}")
	public String activateCustomer(@PathVariable Long id) {
		adminService.activateCustomer(id);
		return "redirect:/api/admin/customers"; // Redirect to customer list after activation
	}

	// Deactivate a customer from customerList
	@PostMapping("/customers/deactivate/{id}")
	public String deactivateCustomer(@PathVariable Long id) {
		adminService.deactivateCustomer(id);
		return "redirect:/api/admin/customers"; // Redirect to customer list after deactivation
	}

	// Get a customer by ID from customerList
	@GetMapping("/customers/{id}")
	public String getCustomerById(@PathVariable Long id, Model model) {
		CustomerAccountDTO customer = adminService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "customerDetails"; // Return the JSP page name to display customer details
	}

	// Get all accounts list
	@GetMapping("/accounts")
	public String getAllAccounts(Model model) {
		List<AccountDTO> accounts = adminService.getAllAccounts();
		model.addAttribute("accounts", accounts);
		return "accountList"; // accountList.jsp
	}
  
	@GetMapping("/customers/{customerId}/accounts/create")
	public String showCreateAccountPage(@PathVariable Long customerId, Model model) {
		model.addAttribute("customerId", customerId); // Pass the customer ID to the view
		model.addAttribute("accountDTO", new AccountDTO()); // Initialize an empty AccountDTO object
		return "createAccount"; // Return the JSP page name for creating an account
	}
 
	// Create a new account for a specific customer
	@PostMapping("/customers/{customerId}/accounts")
	public String createAccount(@PathVariable Long customerId, @ModelAttribute AccountDTO accountDTO) {
		adminService.createAccount(customerId, accountDTO);
		return "redirect:/api/admin/accounts"; // Redirect to the accounts page after creation
	}  

	// Update an existing account
	@PutMapping("/accounts/update/{accountId}")
	public String updateAccount(@PathVariable("accountId") Long accountId, @ModelAttribute AccountDTO accountDTO) {
		adminService.updateAccount(accountId, accountDTO);
		return "redirect:/api/admin/accounts"; // Redirect to the accounts list page after updating
	}
 
	// Delete an account
	@PostMapping("/accounts/{accountId}")
	public String deleteAccount(@PathVariable Long accountId) {
		adminService.deleteAccount(accountId);
		return "redirect:/api/admin/accounts"; // Redirect to the accounts list page after deletion
	}
}
