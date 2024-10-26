package com.myfinbank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myfinbank.customer.model.Account;
import com.myfinbank.customer.model.Loan;
import com.myfinbank.customer.service.AccountService;
import com.myfinbank.customer.service.LoanService;

@Controller
@RequestMapping("/api/customer")
public class CustomerLoanController {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private AccountService accountService;

	
	@GetMapping("/calculate-emi")
	public String showEmiCalculator(Model model) {
		return "calculate-emi"; // Return the JSP view name for the EMI calculator
	}
	
	@GetMapping("/applyLoan")
	public String showApplyLoanForm(Model model) {
	    // You can add attributes to the model if needed
	    return "apply-loan"; // Return the JSP view name for the loan application form
	}
	
	@PostMapping("/applyLoan")
	public String applyForLoan(@RequestParam Long accountId, @RequestParam Double amount, Model model) {
	    try {
	        Account account = accountService.findAccountById(accountId)
	                .orElseThrow(() -> new RuntimeException("Account not found"));
	        loanService.applyForLoan(account, amount);
	        model.addAttribute("successMessage", "Loan application submitted successfully!");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", e.getMessage());
	    }
	    return "apply-loan"; // Return to the apply loan page
	}

//	@GetMapping("/GetloanStatus")
//	public String showLoanStatusForm(Model model) {
//	    // You can add attributes to the model if needed
//	    return "apply-loan"; // Return the JSP view name for the loan application form
//	}
//	@GetMapping("/GetloanStatus/{accountId}")
//	public String getLoanStatus(@RequestParam Long accountId, Model model) {
//	    List<Loan> loans = loanService.findLoansByAccountId(accountId);
//	    model.addAttribute("loans", loans);
//	    return "loan-status"; // Return to the loan status page
//	}
}
