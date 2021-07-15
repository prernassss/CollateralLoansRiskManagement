package com.cognizant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.cognizant.model.Customer;
import com.cognizant.repository.CustomerRepo;

/**
 * 
 * @author rawat
 *
 *Service implementation class for CustomerDetailsService
 *
 */
@Service
@Slf4j
public class CustomerDetailService implements UserDetailsService {
	
	@Autowired
	CustomerRepo customerRepo;

	
	/**
	 * Overriding method to load the customer details from database with user name
	 * 
	 * @param userName
	 * @return This returns the user name and password
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer cust=customerRepo.findByUserName(username);
		return new User(cust.getUserName(), cust.getPassword(), new ArrayList<>());
	}
	
	public Customer loadCustomerByUsername(String username) {
		Customer cust= customerRepo.findByUserName(username);
		log.info("customer = {}", cust);
		return cust;
	}

}