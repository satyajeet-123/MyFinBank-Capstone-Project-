package com.myfinbank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myfinbank.customer.model.Investment;
import com.myfinbank.customer.service.InvestmentService;

@Controller
@RequestMapping("/api/customer/investments")
public class CustomerInvestmentController {
	
	
	@Autowired
	private InvestmentService investmentService;

	@GetMapping("/invest")
	public String showInvestmentForm() {
	    return "invest"; // Return the JSP view name for the investment form
	}
	 
	@PostMapping("/invest")
    public String invest(@RequestParam Long accountId,
                         @RequestParam String investmentType,
                         @RequestParam double amount,
                         Model model) {
        Investment investment = investmentService.invest(accountId, investmentType, amount);
        if (investment != null) {
            model.addAttribute("successMessage", "Investment successful! Investment ID: " + investment.getId());
        } else {
            model.addAttribute("errorMessage", "Investment failed. Please check your details.");
        }
        return "redirect:/api/customer/dashboard"; // Redirect to investments page with accountId
    }

    @GetMapping("/investment")
    public String getInvestments(@RequestParam Long accountId, Model model) {
        List<Investment> investments = investmentService.getInvestmentsByAccountId(accountId);
        model.addAttribute("investments", investments);
        return "view-investments"; // Return the JSP view name for investments
    }

}
