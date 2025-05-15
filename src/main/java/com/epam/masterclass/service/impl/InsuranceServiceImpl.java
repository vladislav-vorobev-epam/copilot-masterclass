package com.epam.masterclass.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InsuranceRepository;
import com.epam.masterclass.service.InsuranceService;

/**
 * Implementation of InsuranceService with Spring annotations.
 */
@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {
    
    private final InsuranceRepository insuranceRepository;
    
    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    
    @Override
    public Insurance createInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }
    
    @Override
    public Optional<Insurance> getInsuranceById(UUID id) {
        return insuranceRepository.findById(id);
    }
    
    @Override
    public Optional<Insurance> getInsuranceByPolicyNumber(String policyNumber) {
        return insuranceRepository.findByPolicyNumber(policyNumber);
    }
    
    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }
    
    @Override
    public List<Insurance> getInsurancesByType(String type) {
        return insuranceRepository.findByType(type);
    }
    
    @Override
    public List<Insurance> getInsurancesByInsuredPersonId(UUID insuredPersonId) {
        return insuranceRepository.findByInsuredPersonId(insuredPersonId);
    }
    
    @Override
    public Insurance updateInsurance(Insurance insurance) {
        if (insurance.getId() == null) {
            throw new IllegalArgumentException("Insurance ID cannot be null for update operation");
        }
        
        Insurance updatedInsurance = insuranceRepository.update(insurance);
        if (updatedInsurance == null) {
            throw new IllegalArgumentException("Insurance with ID " + insurance.getId() + " not found");
        }
        
        return updatedInsurance;
    }
    
    @Override
    public void deleteInsurance(UUID id) {
        insuranceRepository.deleteById(id);
    }
}
