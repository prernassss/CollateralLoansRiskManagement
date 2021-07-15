package com.cts.training.collateralwebportal.feign;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.training.collateralwebportal.model.CashDeposit;
import com.cts.training.collateralwebportal.model.CustomerLoan;
import com.cts.training.collateralwebportal.model.LoanApplication;
import com.cts.training.collateralwebportal.model.RealEstate;

@FeignClient(name = "loan-management", url = "http://localhost:8100/loan/loan-management")
public interface LoanManagementClient {

        /**
         * get loan details
         * 
         * @param token
         * @param loanId
         * @param customerId
         * @return
         */
        @GetMapping(value = "/getLoanDetails/{loanId}/{customerId}")
        public CustomerLoan getLoanDetails(@RequestHeader(name = "Authorization") String token,
                        @PathVariable int loanId, @PathVariable int customerId);

        /**
         * save realestate collateral
         * 
         * @param token
         * @param realEstate
         * @return
         */
        @PostMapping(value = "/saveRealEstateCollateral")
        public ResponseEntity<String> saveRealEsateCollateral(@RequestHeader(name = "Authorization") String token,
                        @RequestBody RealEstate realEstate);

        /**
         * save cash colleteral
         * 
         * @param token
         * @param cashDeposit
         * @return
         */
        @PostMapping(value = "/saveCashDepositCollateral")
        public ResponseEntity<String> saveCashDepositCollateral(@RequestHeader(name = "Authorization") String token,
                        @RequestBody CashDeposit cashDeposit);

        /**
         * apply loan
         * 
         * @param loanApplication
         * @return
         */
        @PostMapping(value = "/applyLoan")
        public ResponseEntity<String> applyLoan(@RequestBody LoanApplication loanApplication);

        /**
         * get application status
         * 
         * @param custId
         * @return
         */
        @GetMapping(value = "/getLoanApplicationStatus")
        public ArrayList<LoanApplication> viewCustLoan(@RequestHeader int custId);

        /**
         * get all applications
         * 
         * @return
         */
        @GetMapping(value = "/getAll")
        public ArrayList<LoanApplication> getAllApplications();

        /**
         * approve application
         * 
         * @param applicationId
         * @return
         */
        @GetMapping(value = "/approveLoanApplication/{applicationId}")
        public ResponseEntity<String> approveLoan(@RequestHeader Integer applicationId);

        /**
         * reject application
         * 
         * @param applicationId
         * @return response entity
         */
        @GetMapping(value = "/rejectLoanApplication/{applicationId}")
        public ResponseEntity<String> rejectLoan(@RequestHeader Integer applicationId);

}