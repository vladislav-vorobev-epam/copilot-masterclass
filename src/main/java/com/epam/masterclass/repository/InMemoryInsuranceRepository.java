package com.epam.masterclass.repository;

import com.epam.masterclass.model.Insurance;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the InsuranceRepository.
 */
public class InMemoryInsuranceRepository implements InsuranceRepository {
    
    // In-memory storage for insurances
    private final Map<UUID, Insurance> insurances = new HashMap<>();
    
    @Override
    public Insurance save(Insurance insurance) {
        if (insurance.getId() == null) {
            insurance.setId(UUID.randomUUID());
        }
        insurances.put(insurance.getId(), insurance);
        return insurance;
    }
    
    @Override
    public Optional<Insurance> findById(UUID id) {
        return Optional.ofNullable(insurances.get(id));
    }
    
    @Override
    public Optional<Insurance> findByPolicyNumber(String policyNumber) {
        return insurances.values().stream()
                .filter(insurance -> Objects.equals(insurance.getPolicyNumber(), policyNumber))
                .findFirst();
    }
    
    @Override
    public List<Insurance> findAll() {
        return new ArrayList<>(insurances.values());
    }
    
    @Override
    public List<Insurance> findByType(String type) {
        return insurances.values().stream()
                .filter(insurance -> Objects.equals(insurance.getType(), type))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Insurance> findByInsuredPersonId(UUID insuredPersonId) {
        return insurances.values().stream()
                .filter(insurance -> Objects.equals(insurance.getInsuredPersonId(), insuredPersonId))
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        insurances.remove(id);
    }
    
    @Override
    public Insurance update(Insurance insurance) {
        if (insurance.getId() == null || !insurances.containsKey(insurance.getId())) {
            return null;
        }
        
        insurances.put(insurance.getId(), insurance);
        return insurance;
    }
}
