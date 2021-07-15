package com.cts.training.collateralwebportal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.cts.training.collateralwebportal.model.LoginModel;

public interface LoginService {

    /**
     * 
     * @param token
     * @return
     */
    Boolean validate(String token);

    /**
     * 
     * @param model
     * @return
     */
    String login(LoginModel model);

    /**
     * 
     * @param request
     * @return
     */
    public ModelAndView checkStatus(HttpServletRequest request);

    /**
     * 
     * @param request
     * @return
     */
    public ModelAndView checkStatusCustomer(HttpServletRequest request);

    /**
     * 
     * @param model
     * @return
     */
    String loginCustomer(LoginModel model);

    /**
     * 
     * @param token
     * @return
     */
    public int getCustId(String token);

}