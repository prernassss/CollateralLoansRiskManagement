package com.cts.training.collateralwebportal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.training.collateralwebportal.feign.AuthClient;
import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.model.CustomerLoan;
import com.cts.training.collateralwebportal.model.CustomerLoanDto;
import com.cts.training.collateralwebportal.model.LoanApplication;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerLoanController {

	@Autowired
	private LoanManagementClient loanClient;

	@Autowired
	private AuthClient authClient;

	String customerLoan = "customerloan";
	String tokenStr = "token";


	/**
	 * 
	 * @param customloan
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/customerloan")
	public String show(@ModelAttribute("customloan") CustomerLoanDto customloan, ModelMap model) {
		return customerLoan;
	}


	/**
	 * 
	 * @param customloan
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/customerloan")
	public String submitLoan(@Valid @ModelAttribute("customloan") CustomerLoanDto customloan, BindingResult result,
			ModelMap model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.put("errorMessage", "Invalid Customer Loan Details!");
			return customerLoan;
		}
		String token = "Bearer " + (String) request.getSession().getAttribute(tokenStr);
		CustomerLoan loan = null;

		try {
			loan = loanClient.getLoanDetails(token, customloan.getLoanId(), customloan.getCustomerId());
			log.info("================inside Customer Loan=====================");
			model.addAttribute("loan", loan);
			return customerLoan;
		} catch (FeignException e) {
			if (e.getMessage().contains("Customer Loan Not found with LoanId")) {
				model.addAttribute("status", "Customer Loan Not found!!");
			}
			return customerLoan;
		}

	}

	/**
	 * 
	 * @param loanApplication
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/applyloan")
	public String applyLoanGet(@ModelAttribute("loanApplication") LoanApplication loanApplication, ModelMap model,
			HttpServletRequest request) {

		// for getting session token to get the custId
		String token = (String) request.getSession().getAttribute(tokenStr);
		int custId = authClient.getCustId(token);
		model.put("custId", custId);
		request.setAttribute("custId", custId);

		return "applyLoan";
	}


	/**
	 * 
	 * @param loanApplication
	 * @param model
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/applyloan")
	public String applyLoanPost(@Valid @ModelAttribute("loanApplication") LoanApplication loanApplication,
			ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "applyLoan";
		}
		loanClient.applyLoan(loanApplication);
		return "successfullyAppliedLoan";
	}


	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/getLoanApplicationStatus")
	public String viewLoanCust(ModelMap model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute(tokenStr);
		int custId = authClient.getCustId(token);
		ArrayList<LoanApplication> list = loanClient.viewCustLoan(custId);
		if (!list.isEmpty()) {
			model.put("LoanList", list);
			return "present";
		}

		return "noloanpresent";

	}


	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/getAll")
	public String getLoanApplications(ModelMap model) {
		ArrayList<LoanApplication> list = loanClient.getAllApplications();
		if (!list.isEmpty()) {
			model.addAttribute("LoanList", list);
			return "adminloanapplication";
		}

		return "noloanpresentadmin";
	}


	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	@GetMapping(value = "/approveLoanApplication")
	public String approveLoanApplication(@RequestParam int applicationId) {
		log.info("Accepted");
		loanClient.approveLoan(applicationId);
		return "redirect:/getAll";
	}

	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	@GetMapping(value = "/rejectLoanApplication")
	public String rejectLoanApplication(@RequestParam int applicationId) {
		log.info("Rejected");
		loanClient.rejectLoan(applicationId);
		return "redirect:/getAll";
	}

	/**
	 * collateral list
	 * @return cash deposit or real state
	 */
	@ModelAttribute("collateralList")
	public List<String> getWebFrameworkList() {
		List<String> collateralList = new ArrayList<>();
		collateralList.add("Cash Deposit");
		collateralList.add("Real Estate");

		return collateralList;
	}

}