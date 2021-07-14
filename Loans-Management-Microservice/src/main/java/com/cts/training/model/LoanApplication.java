package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "loanApplication")
@ApiModel("Model class for loan application")
@SequenceGenerator(name="seq", initialValue=100, allocationSize=1000)
public class LoanApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @ApiModelProperty("Id of Loan application")
    private Integer applicationId;

    @ApiModelProperty("Id of customer")
    private Integer customerId;

    @ApiModelProperty("Loan amount of loan application")
    private Double loanAmount;

    @ApiModelProperty("Tenure Loan application")
    private Integer tenure;

    @ApiModelProperty("Collateral Details of Loan application")
    private String collateralDetails;

    @ApiModelProperty("Status of Loan application")
    private String status;

}
