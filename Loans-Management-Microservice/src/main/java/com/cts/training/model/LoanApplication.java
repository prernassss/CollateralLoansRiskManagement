package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class LoanApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer applicationId;
    private Integer customerId;
    private Double loanAmount;
    private Integer tenure;
    private String collateralDetails;
    private String status;

}
