package com.cts.training.collateralwebportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    /**
     * Admin Home Page
     * 
     * @return modelandview admin home
     */
    @GetMapping("/home")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    /**
     * Customer Home Page
     * 
     * @return modelandview customer home
     */
    @GetMapping("/homeCustomer")
    public ModelAndView homePageCustomer() {
        return new ModelAndView("homecustomer");
    }

}