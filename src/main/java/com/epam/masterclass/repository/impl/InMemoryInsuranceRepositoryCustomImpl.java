package com.epam.masterclass.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InsuranceRepositoryCustom;

/**
 * In-memory implementation of the InsuranceRepositoryCustom for development use.
 * This implementation is used when the 'dev' profile is active.
 */
@Repository
@Profile("dev")
public class InMemoryInsuranceRepositoryCustomImpl implements InsuranceRepositoryCustom {
    
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
    public boolean existsById(UUID id) {
        return insurances.containsKey(id);
    }
}
