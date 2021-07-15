package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cts.training.collateralwebportal.model.LoginModel;
import com.cts.training.collateralwebportal.service.LoginService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LoginController {

	private static final String MODEL_STR = "model";
	private static final String STATUS_STR = "status";
	private static final String INVALID_STR = "Invalid Credentials !!";

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject(MODEL_STR, new LoginModel());
		return mv;
	}

	@GetMapping("/loginAdmin")
	public ModelAndView showLoginAdmin() {
		ModelAndView mv = new ModelAndView("loginadmin");
		mv.addObject(MODEL_STR, new LoginModel());
		return mv;
	}

	@GetMapping("/loginCustomer")
	public ModelAndView showLoginCustomer() {
		ModelAndView mv = new ModelAndView("logincustomer");
		mv.addObject(MODEL_STR, new LoginModel());
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView performLogin(@Valid @ModelAttribute("model") LoginModel loginModel, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		log.info("BEGIN   -   [afterLogin()]");
		ModelAndView mv = new ModelAndView("loginadmin");
		try {
			if (result.hasErrors()) {
				return mv;
			}
			String token = loginService.login(loginModel);
			request.getSession().setAttribute("token", token);
			mv.setViewName("home");
			return mv;
		} catch (FeignException e) {
			if ((e.getMessage().contains("User name")) || (e.getMessage().contains("Password is wrong"))
					|| (e.getMessage().contains("Invalid Credential"))) {
				model.addAttribute(STATUS_STR, INVALID_STR);
			}
			return mv;
		}
	}

	/**
	 * customer login post mapping method
	 * 
	 * @param loginModel
	 * @param result
	 * @param model
	 * @param request
	 * @return logincustomer on error else customer home
	 */
	@PostMapping("/loginCustomer")
	public ModelAndView performCustomerLogin(@Valid @ModelAttribute("model") LoginModel loginModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("logincustomer");
		try {
			if (result.hasErrors()) {
				return mv;
			}
			String token = loginService.loginCustomer(loginModel);
			request.getSession().setAttribute("token", token);

			// for custId
			int custId = loginService.getCustId(token);
			log.info("custId = {}", custId);

			mv.setViewName("homecustomer");
			return mv;
		} catch (FeignException e) {
			if ((e.getMessage().contains("User name")) || (e.getMessage().contains("Password is wrong"))
					|| (e.getMessage().contains("Invalid Credential"))) {
				model.addAttribute(STATUS_STR, INVALID_STR);
			}
			return mv;
		}

	}
}