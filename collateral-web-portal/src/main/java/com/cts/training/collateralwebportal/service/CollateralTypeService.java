package com.cts.training.collateralwebportal.service;

import org.springframework.stereotype.Service;

@Service
public class CollateralTypeService {

    /**
     * 
     * @param collateralType
     * @return
     */
    public boolean checkCollateral(String collateralType) {
        return (collateralType.equalsIgnoreCase("RealEstate"));
       
    }
}
