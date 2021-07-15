package com.cts.training.collateralwebportal.feign;

import com.cts.training.collateralwebportal.model.LoginModel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Feign client for collateral web portal service
 */
@FeignClient(name = "auth-client", url = "http://localhost:8081/auth")
public interface AuthClient {
    
    /**
     * 
     * @param token
     * @return
     */
    @GetMapping("/validate")
    public boolean validate(String token);

    /**
     * 
     * @param token
     * @return
     */
    @GetMapping("/validateCustomer")
    public boolean validateCustomer(String token);

    /**
     * 
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(LoginModel model);
    
    /**
     * 
     * @param model
     * @return
     */
    @PostMapping("/loginCustomer")
    public String loginCustomer(LoginModel model);
    
    /**
     * 
     * @param token1
     * @return
     */
    @GetMapping("/getCustId")
	public int getCustId(@RequestHeader(name = "Authorization") String token1);
}