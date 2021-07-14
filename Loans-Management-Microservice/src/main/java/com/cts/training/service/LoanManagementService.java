package com.cts.training.service;

import org.springframework.http.ResponseEntity;

import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.exception.LoanApplicationNotFound;
import com.cts.training.exception.LoanNotFoundException;
import com.cts.training.model.CustomerLoan;
import com.cts.training.model.LoanApplication;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.RealEstate;
import com.cts.training.repo.LoanApplicationRepo;

/**
 * LoanManagementService Interface for the loan management functionality
 */
public interface LoanManagementService {
	/**
	 * For Fetching the loan details
	 * 
	 * @param loanId
	 * @param customerId
	 * @return CustomerLoan
	 * @throws CustomerLoanNotFoundException
	 */
	public CustomerLoan getLoanDetails(int loanId, int customerId) throws CustomerLoanNotFoundException;

	/**
	 * For Saving RealEstate Estate
	 * 
	 * @param token
	 * @param realEstate
	 * @return ResponseEntity/Status
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	public ResponseEntity<String> saveRealEstate(String token, RealEstate realEstate)
			throws CustomerLoanNotFoundException, LoanNotFoundException;

	/**
	 * For Saving CashDeposit Estate
	 * 
	 * @param token
	 * @param cashDeposit
	 * @return
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	public ResponseEntity<String> saveCashDeposit(String token, CashDeposit cashDeposit)
			throws CustomerLoanNotFoundException, LoanNotFoundException;

	/**
	 * For getting the status of loan application
	 * @param applicationId
	 * @return
	 * @throws LoanApplicationNotFound
	 */
	public ResponseEntity<LoanApplication> getLoanApplicationStatus(Integer applicationId)
			throws LoanApplicationNotFound;

	/**
	 * For applying loan
	 * @param loanApplication
	 * @return
	 */
	public ResponseEntity<String> applyLoan(LoanApplication loanApplication);

	/**
	 * For approving loan
	 * @param applicationId
	 * @return
	 * @throws LoanApplicationNotFound
	 */
	public ResponseEntity<String> approveLoanApplication(Integer applicationId) throws LoanApplicationNotFound;

	/**
	 * For rejecting loan
	 * @param applicationId
	 * @return
	 * @throws LoanApplicationNotFound
	 */
	public ResponseEntity<String> rejectLoanApplication(Integer applicationId) throws LoanApplicationNotFound;
}
