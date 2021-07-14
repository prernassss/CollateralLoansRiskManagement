package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "loanApplication")
@ApiModel("Model class for loan application")
public class LoanApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
