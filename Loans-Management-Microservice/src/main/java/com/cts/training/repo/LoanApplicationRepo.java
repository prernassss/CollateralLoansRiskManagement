package com.cts.training.repo;

import com.cts.training.model.LoanApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepo extends JpaRepository<LoanApplication,Integer> {
    
}
