package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.model.CashDeposit;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CashDepositController {

	
	@Autowired
	private LoanManagementClient loanClient;

	String cashDeposit = "cashdeposit";

	/**
	 * get method for showing the cash deposit page
	 * @param cash
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/cashdeposit")
	public String showCollateral(@ModelAttribute("cash") CashDeposit cash, ModelMap model) {
		return cashDeposit;
	}

	/**
	 * post method to deposit the cash
	 * @param cash
	 * @param res
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/cashdeposit")
	public String addUser(@Valid @ModelAttribute("cash") CashDeposit cash, BindingResult res, ModelMap model,
			HttpServletRequest request) {
		if (res.hasErrors()) {
			model.put("errorMessage", "Invalid Cash Details!");
			return cashDeposit;
		}
		String token = "Bearer " + (String) request.getSession().getAttribute("token");
		ResponseEntity<String> status = null;
		String statusStr = "status";
		try {
			log.info("cash loan id = {}", cash.getLoanId());
			status = loanClient.saveCashDepositCollateral(token, cash);
		}  catch (FeignException e) {
			if(e.getMessage().contains("Collateral Mismatch")) {
				model.addAttribute(statusStr, "Collateral Mismatch! Try with Valid Data.");
			}
			else if(e.getMessage().contains("Customer Loan Not found with LoanId")) {
				model.addAttribute(statusStr, "Input Mismatch! Try with Valid Data.");
			}
			else if(e.getMessage().contains("Collateral already exists with loan id")) {
				model.addAttribute(statusStr, "Collateral already exists with loan ID");
			}
			return cashDeposit;
		}

		String body = status.getBody();
		log.error(body);
		model.addAttribute(statusStr,"Saved Successfully!");

		return cashDeposit;
	}
}