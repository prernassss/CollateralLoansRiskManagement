package com.cts.training.exception;

public class LoanApplicationNotFound extends Exception {
    
    private static final long serialVersionUID = 1L;
    /**
     * Loan application not found exception
     * @param message
     */
    public LoanApplicationNotFound(String message){
        super(message);
    }
    
}
