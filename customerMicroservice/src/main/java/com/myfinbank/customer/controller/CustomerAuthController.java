package com.myfinbank.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.service.CustomerService;
import com.myfinbank.customer.service.CustomersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/customer")
public class CustomerAuthController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomersService customersService;

	@Autowired
	private CustomerRepository customerRepository;

	// Customer Registration
		@GetMapping("/register")
		public String showRegistrationForm(Model model) {
			model.addAttribute("customer", new Customer());
			return "customer-register"; // JSP view for customer registration
		}

		@PostMapping("/register")
		public String registerCustomer(@ModelAttribute Customer customer, Model model) {
			customerService.register(customer);
			model.addAttribute("successMessage", "Customer registered successfully!");
			return "redirect:/api/customer/login"; // Redirect to login page after registration
		}

		// Customer Login
		@GetMapping("/login")
		public String login() {
			return "customer-login"; // JSP view for customer login
		}

		@PostMapping("/login")
		public String login(@ModelAttribute Customer customer, Model model) {
			// Fetch the customer from the database
			Optional<Customer> optionalCustomer = customerRepository.findByUsername(customer.getUsername());

			// Check if the customer exists and the password matches
			if (optionalCustomer.isPresent() && customer.getPassword().equals(optionalCustomer.get().getPassword())) {
				// Optionally, set the customer in the session or perform additional actions
				return "redirect:/api/customer/dashboard"; // Redirect to dashboard on success
			} else {
				model.addAttribute("error", "Invalid username or password.");
				return "customer-login"; // Return to login page on failure
			}
		}

		@GetMapping("/dashboard")
		public String showDashboard(Model model, HttpSession session) {
			// Example: Retrieve customer ID from session
			Long customerId = (Long) session.getAttribute("customerId");
			if (customerId != null) {
				Customer customer = customerService.findByCustomerId(customerId)
						.orElseThrow(() -> new RuntimeException("Customer not found"));
				model.addAttribute("customer", customer); // Add customer details to model
			}
			return "customer-dashboard"; // Return customer-dashboard.jsp
		}

		// Customer Logout
		@PostMapping("/logout")
		public String logout(Model model) {
			return "redirect:/api/customer/login"; // Redirect to login page
		}
}
