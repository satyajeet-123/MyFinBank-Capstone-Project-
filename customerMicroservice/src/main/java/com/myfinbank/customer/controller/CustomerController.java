package com.myfinbank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myfinbank.customer.dto.AccountDTO;
import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Transaction;
import com.myfinbank.customer.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:58837")
public class CustomerController {

	@Autowired
	private AccountService accountService;

//	@Autowired
//	private CustomerService customerService;
//	@Autowired
//	private InvestmentService investmentService;
//	@Autowired
//	private LoanService loanService;
//	@Autowired
//	private CustomerRepository customerRepository;
//	// Customer Registration
//	@GetMapping("/register")
//	public String showRegistrationForm(Model model) {
//		model.addAttribute("customer", new Customer());
//		return "customer-register"; // JSP view for customer registration
//	}
//	@PostMapping("/register")
//	public String registerCustomer(@ModelAttribute Customer customer, Model model) {
//		customerService.register(customer);
//		model.addAttribute("successMessage", "Customer registered successfully!");
//		return "redirect:/api/customer/login"; // Redirect to login page after registration
//	}
//	// Customer Login
//	@GetMapping("/login")
//	public String login() {
//		return "customer-login"; // JSP view for customer login
//	}
//	@PostMapping("/login")
//	public String login(@ModelAttribute Customer customer, Model model) {
//		// Fetch the customer from the database
//		Optional<Customer> optionalCustomer = customerRepository.findByUsername(customer.getUsername());
//
//		// Check if the customer exists and the password matches
//		if (optionalCustomer.isPresent() && customer.getPassword().equals(optionalCustomer.get().getPassword())) {
//			// Optionally, set the customer in the session or perform additional actions
//			return "redirect:/api/customer/dashboard"; // Redirect to dashboard on success
//		} else {
//			model.addAttribute("error", "Invalid username or password.");
//			return "customer-login"; // Return to login page on failure
//		}
//	}
//	@GetMapping("/dashboard")
//	public String showDashboard(Model model, HttpSession session) {
//		// Example: Retrieve customer ID from session
//		Long customerId = (Long) session.getAttribute("customerId");
//		if (customerId != null) {
//			Customer customer = customerService.findByCustomerId(customerId)
//					.orElseThrow(() -> new RuntimeException("Customer not found"));
//			model.addAttribute("customer", customer); // Add customer details to model
//		}
//		return "customer-dashboard"; // Return customer-dashboard.jsp
//	}
//	// Customer Logout
//	@PostMapping("/logout")
//	public String logout(Model model) {
//		return "redirect:/api/customer/login"; // Redirect to login page
//	}

//	// account details
//	@GetMapping("/account/details")
//	public ModelAndView showAccountDetails(Model model, HttpSession session,
//			@ModelAttribute("account") AccountDTO accountDto) {
//		ModelAndView modelAndView = new ModelAndView("account-details");
////			Long accountId = (Long) session.getAttribute("accountId"); // Retrieve account ID from session
//		Long accountId = 4L;
//		if (accountId != null) {
//			Account account = accountService.findAccountById(accountId)
//					.orElseThrow(() -> new RuntimeException("Account not found"));
//			accountDto.setAccountNumber(account.getAccountNumber());
//			accountDto.setBalance(account.getBalance());
//			// model.addAttribute("account", account); // Pass account data to view
//			modelAndView.addObject("account", accountDto);
//		}
//		return modelAndView; // Return account-details.jsp
//	}

//	@GetMapping("/account/details")
//	public ModelAndView showAccountDetails(@PathVariable Long accountId, @ModelAttribute("account") AccountDTO accountDto) {
//	    ModelAndView modelAndView = new ModelAndView("account-details");
//	    Long accountId = 4L;
//	    Account account = Long accountId = 4L;
////	    		accountService.findAccountById(accountId)
//	            .orElseThrow(() -> new RuntimeException("Account not found"));
//	    
//	    accountDto.setAccountNumber(account.getAccountNumber());
//	    accountDto.setBalance(account.getBalance());
//	    
//	    modelAndView.addObject("account", accountDto);
//	    
//	    return modelAndView; // Return account-details.jsp
//	}

//	// Deposit Funds 
//	@GetMapping("/deposit")
//	public String showDepositForm(@RequestParam Long accountId, Model model) {
//		Account account = accountService.findAccountById(accountId)
//				.orElseThrow(() -> new RuntimeException("Account not found"));
//		model.addAttribute("accountId", accountId);
//		return "deposit"; // Return deposit JSP
//	}

	// Get all accounts list
	@GetMapping("/accounts")
	public String getAllAccounts(Model model) {
		List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		return "accountList"; // accountList.jsp
	}

	@GetMapping("/deposit")
	public String showDepositForm() {
		return "deposit";
	}

	@PostMapping("/deposit")
	public String deposit(@RequestParam Long accountId, @RequestParam Double amount, Model model) {
		Account account = accountService.findAccountById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		if (account != null) {
			accountService.deposit(account, amount);
			model.addAttribute("account", account);
			model.addAttribute("message", "Deposit successful!");
		} else {
			model.addAttribute("message", "Account not found.");
		}
		return "redirect:/api/customer/deposit"; // Redirect after deposit
	}

	// Withdraw Funds
	@GetMapping("/withdraw")
	public String showWithdrawForm() {
		return "withdraw"; // Return withdraw JSP
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam Long accountId, @RequestParam Double amount, Model model) {
		Account account = accountService.findAccountById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		if (accountService.withdraw(account, amount) != null) {
			model.addAttribute("message", "Withdrawal successful!");
		} else {
			model.addAttribute("error", "Insufficient funds!");
		}
		return "redirect:/api/customer/withdraw"; // Redirect after withdrawal
	}

	@GetMapping("/transactions")
	public String showTransactionForm() {
		return "view-transactions"; // Return transfer JSP
	}

	@GetMapping("/transactions/{accountId}")
	public String getTransactionHistory(@RequestParam Long accountId, Model model) {
		List<Transaction> transactions = accountService.getTransactionsHistory(accountId);
		model.addAttribute("transactions", transactions);
		model.addAttribute("accountId", accountId); // Add accountId if needed for further operations
		return "transactionhistory"; // Return the name of the JSP file
	}

	// Transfer Funds
	@GetMapping("/transfer")
	public String showTransferForm() {
		return "transfer"; // Return transfer JSP
	}

	@PostMapping("/transfer")
	public String transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId,
			@RequestParam Double amount, Model model) {
		Account fromAccount = accountService.findAccountById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("From Account not found"));
		Account toAccount = accountService.findAccountById(toAccountId)
				.orElseThrow(() -> new RuntimeException("To Account not found"));
		if (accountService.transfer(fromAccount, toAccount, amount) != null) {
			model.addAttribute("message", "Transfer successful!");
		} else {
			model.addAttribute("error", "Transfer failed! Check balance and account IDs.");
		}
		return "redirect:/api/customer/transfer"; // Redirect after transfer
	}

//	@Autowired
//	private CustomerService customerService;
//	@Autowired
//	private InvestmentService investmentService;
//	@Autowired
//	private LoanService loanService;
//	@Autowired
//	private CustomerRepository customerRepository;
//	// Customer Registration
//	@GetMapping("/register")
//	public String showRegistrationForm(Model model) {
//		model.addAttribute("customer", new Customer());
//		return "customer-register"; // JSP view for customer registration
//	}
//	@PostMapping("/register")
//	public String registerCustomer(@ModelAttribute Customer customer, Model model) {
//		customerService.register(customer);
//		model.addAttribute("successMessage", "Customer registered successfully!");
//		return "redirect:/api/customer/login"; // Redirect to login page after registration
//	}
//	// Customer Login
//	@GetMapping("/login")
//	public String login() {
//		return "customer-login"; // JSP view for customer login
//	}
//	@PostMapping("/login")
//	public String login(@ModelAttribute Customer customer, Model model) {
//		// Fetch the customer from the database
//		Optional<Customer> optionalCustomer = customerRepository.findByUsername(customer.getUsername());
//
//		// Check if the customer exists and the password matches
//		if (optionalCustomer.isPresent() && customer.getPassword().equals(optionalCustomer.get().getPassword())) {
//			// Optionally, set the customer in the session or perform additional actions
//			return "redirect:/api/customer/dashboard"; // Redirect to dashboard on success
//		} else {
//			model.addAttribute("error", "Invalid username or password.");
//			return "customer-login"; // Return to login page on failure
//		}
//	}
//	@GetMapping("/dashboard")
//	public String showDashboard(Model model, HttpSession session) {
//		// Example: Retrieve customer ID from session
//		Long customerId = (Long) session.getAttribute("customerId");
//		if (customerId != null) {
//			Customer customer = customerService.findByCustomerId(customerId)
//					.orElseThrow(() -> new RuntimeException("Customer not found"));
//			model.addAttribute("customer", customer); // Add customer details to model
//		}
//		return "customer-dashboard"; // Return customer-dashboard.jsp
//	}
//	// Customer Logout
//	@PostMapping("/logout")
//	public String logout(Model model) {
//		return "redirect:/api/customer/login"; // Redirect to login page
//	}
//	@GetMapping("/investments/invest")
//	public String showInvestmentForm() {
//	    return "invest"; // Return the JSP view name for the investment form
//	}
//	
//	@PostMapping("/investments/invest")
//    public String invest(@RequestParam Long accountId,
//                         @RequestParam String investmentType,
//                         @RequestParam double amount,
//                         Model model) {
//        Investment investment = investmentService.invest(accountId, investmentType, amount);
//        if (investment != null) {
//            model.addAttribute("successMessage", "Investment successful! Investment ID: " + investment.getId());
//        } else {
//            model.addAttribute("errorMessage", "Investment failed. Please check your details.");
//        }
//        return "redirect:/api/customer/investments?accountId=" + accountId; // Redirect to investments page with accountId
//    }
//
//    @GetMapping("/investments")
//    public String getInvestments(@RequestParam Long accountId, Model model) {
//        List<Investment> investments = investmentService.getInvestmentsByAccountId(accountId);
//        model.addAttribute("investments", investments);
//        return "view-investments"; // Return the JSP view name for investments
//    }

//	@GetMapping("/calculate-emi")
//	public String showEmiCalculator(Model model) {
//		return "calculate-emi"; // Return the JSP view name for the EMI calculator
//	}
//	
//	@GetMapping("/applyLoan")
//	public String showApplyLoanForm(Model model) {
//	    // You can add attributes to the model if needed
//	    return "apply-loan"; // Return the JSP view name for the loan application form
//	}
//	
//	@PostMapping("/applyLoan")
//	public String applyForLoan(@RequestParam Long accountId, @RequestParam Double amount, Model model) {
//	    try {
//	        Account account = accountService.findAccountById(accountId)
//	                .orElseThrow(() -> new RuntimeException("Account not found"));
//	        loanService.applyForLoan(account, amount);
//	        model.addAttribute("successMessage", "Loan application submitted successfully!");
//	    } catch (Exception e) {
//	        model.addAttribute("errorMessage", e.getMessage());
//	    }
//	    return "apply-loan"; // Return to the apply loan page
//	}
//
//	@GetMapping("/loanStatus")
//	public String getLoanStatus(@RequestParam Long accountId, Model model) {
//	    List<Loan> loans = loanService.findLoansByAccountId(accountId);
//	    model.addAttribute("loans", loans);
//	    return "loan-status"; // Return to the loan status page
//	}

//	@PostMapping("/sendMessage")
//	public Chat sendMessageToAdmin(@RequestParam Long customerId, @RequestParam Long adminId,
//			@RequestParam String message) {
//		Customer customer = customerService.findByCustomerId(customerId)
//				.orElseThrow(() -> new RuntimeException("Customer not found"));
//		Admin admin = adminService.findById(adminId).orElseThrow(() -> new RuntimeException("Admin not found"));
//
//		return chatService.sendMessage(customer, admin, "CUSTOMER", message);
//	}
//
//	// Get chat history between customer and admin
//	@GetMapping("/chatHistory/{customerId}/{adminId}")
//	public List<Chat> getChatHistory(@PathVariable Long customerId, @PathVariable Long adminId) {
//		return chatService.getChatHistory(customerId, adminId);
//	}
}
