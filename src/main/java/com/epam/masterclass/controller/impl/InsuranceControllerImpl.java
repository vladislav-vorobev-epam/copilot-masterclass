package com.epam.masterclass.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.epam.masterclass.controller.InsuranceController;
import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.service.InsuranceService;

/**
 * REST Controller implementation of InsuranceController.
 */
@RestController
public class InsuranceControllerImpl implements InsuranceController {
    
    private final InsuranceService insuranceService;
    
    @Autowired
    public InsuranceControllerImpl(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }
    
    @Override
    public ResponseEntity<Insurance> createInsurance(Insurance insurance) {
        Insurance createdInsurance = insuranceService.createInsurance(insurance);
        return new ResponseEntity<>(createdInsurance, HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<Insurance> getInsuranceById(UUID id) {
        return insuranceService.getInsuranceById(id)
            .map(insurance -> new ResponseEntity<>(insurance, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @Override
    public ResponseEntity<Insurance> getInsuranceByPolicyNumber(String policyNumber) {
        return insuranceService.getInsuranceByPolicyNumber(policyNumber)
            .map(insurance -> new ResponseEntity<>(insurance, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @Override
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<List<Insurance>> getInsurancesByType(String type) {
        List<Insurance> insurances = insuranceService.getInsurancesByType(type);
        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<List<Insurance>> getInsurancesByInsuredPersonId(UUID insuredPersonId) {
        List<Insurance> insurances = insuranceService.getInsurancesByInsuredPersonId(insuredPersonId);
        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Insurance> updateInsurance(UUID id, Insurance insurance) {
        // Ensure the ID path variable matches the insurance ID
        insurance.setId(id);
        
        try {
            Insurance updatedInsurance = insuranceService.updateInsurance(insurance);
            return new ResponseEntity<>(updatedInsurance, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Log the error instead of printing to stderr
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Override
    public ResponseEntity<Void> deleteInsurance(UUID id) {
        insuranceService.deleteInsurance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
