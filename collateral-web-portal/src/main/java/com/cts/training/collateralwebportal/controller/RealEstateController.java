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
import com.cts.training.collateralwebportal.model.RealEstate;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RealEstateController {

	@Autowired
	private LoanManagementClient loanClient;

	private static final String REALSTATE_STR = "reakestate";

	@GetMapping(value = "/realestate")
	public String showCollateral(@ModelAttribute("realestate") RealEstate realestate, ModelMap model) {
		return REALSTATE_STR;
	}

	@PostMapping(value = "/realestate")
	public String addUser(@Valid @ModelAttribute("realestate") RealEstate realestate, BindingResult res, ModelMap model,
			HttpServletRequest request) {
		if (res.hasErrors()) {
			model.put("errorMessage", "Invalid Real Estate Details!");
			return REALSTATE_STR;
		}
		String token = "Bearer " + (String) request.getSession().getAttribute("token");
		ResponseEntity<String> status = null;
		try {
			status = loanClient.saveRealEsateCollateral(token, realestate);
		} catch (FeignException e) {
			if (e.getMessage().contains("Collateral Mismatch")
					|| (e.getMessage().contains("Customer Loan Not found with LoanId"))
					|| (e.getMessage().contains("Collateral already exists with loan id"))) {
				model.addAttribute("status", "Collateral Mismatch! Try with Valid Data.");
			}
			return REALSTATE_STR;
		}

		String body = status.getBody();
		log.error("status body  ={}", body);
		model.addAttribute("status", "Saved Successfully!");
		return REALSTATE_STR;
	}

}