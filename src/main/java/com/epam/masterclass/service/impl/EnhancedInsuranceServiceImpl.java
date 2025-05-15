package com.epam.masterclass.service.impl;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InsuranceRepositoryCustom;
import com.epam.masterclass.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Enhanced implementation of InsuranceService with Spring annotations and caching.
 */
@Service("enhancedInsuranceService")
@Transactional
public class EnhancedInsuranceServiceImpl implements InsuranceService {
    
    private final InsuranceRepository insuranceRepository;
    
    @Autowired
    public EnhancedInsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    
    @Override
    @Caching(evict = {
        @CacheEvict(value = "allInsurances", allEntries = true),
        @CacheEvict(value = "insurancesByType", allEntries = true)
    })
    public Insurance createInsurance(Insurance insurance) {
        if (insurance.getId() == null) {
            insurance.setId(UUID.randomUUID());
        }
        return insuranceRepository.save(insurance);
    }
    
    @Override
    @Cacheable(value = "insurancesById", key = "#id")
    public Optional<Insurance> getInsuranceById(UUID id) {
        return insuranceRepository.findById(id);
    }
    
    @Override
    public Optional<Insurance> getInsuranceByPolicyNumber(String policyNumber) {
        return insuranceRepository.findByPolicyNumber(policyNumber);
    }
    
    @Override
    @Cacheable(value = "allInsurances")
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }
    
    @Override
    @Cacheable(value = "insurancesByType", key = "#type")
    public List<Insurance> getInsurancesByType(String type) {
        return insuranceRepository.findByType(type);
    }
    
    @Override
    public List<Insurance> getInsurancesByInsuredPersonId(UUID insuredPersonId) {
        return insuranceRepository.findByInsuredPersonId(insuredPersonId);
    }
    
    @Override
    @Caching(evict = {
        @CacheEvict(value = "insurancesById", key = "#insurance.id"),
        @CacheEvict(value = "allInsurances", allEntries = true),
        @CacheEvict(value = "insurancesByType", allEntries = true)
    })
    public Insurance updateInsurance(Insurance insurance) {
        if (insurance.getId() == null || !insuranceRepository.existsById(insurance.getId())) {
            throw new IllegalArgumentException("Insurance not found with id: " + insurance.getId());
        }
        return insuranceRepository.save(insurance);
    }
    
    @Override
    @Caching(evict = {
        @CacheEvict(value = "insurancesById", key = "#id"),
        @CacheEvict(value = "allInsurances", allEntries = true),
        @CacheEvict(value = "insurancesByType", allEntries = true)
    })
    public boolean deleteInsurance(UUID id) {
        if (!insuranceRepository.existsById(id)) {
            return false;
        }
        insuranceRepository.deleteById(id);
        return true;
    }
}
