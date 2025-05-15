package com.epam.masterclass.repository.impl;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InsuranceRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the InsuranceRepository for development use.
 * This implementation is used when the 'dev' profile is active.
 */
@Repository
@Profile("dev")
public class InMemoryInsuranceRepositoryImpl implements InsuranceRepository {
    
    // In-memory storage for insurances
    private final Map<UUID, Insurance> insurances = new HashMap<>();
    
    @Override
    public <S extends Insurance> S save(S insurance) {
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
    
    // Additional methods required by JpaRepository interface
    
    @Override
    public List<Insurance> findAll(org.springframework.data.domain.Sort sort) {
        return findAll(); // Ignoring sort for in-memory implementation
    }
    
    @Override
    public <S extends Insurance> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        entities.forEach(entity -> result.add(save(entity)));
        return result;
    }
    
    @Override
    public List<Insurance> findAllById(Iterable<UUID> ids) {
        List<Insurance> result = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }
    
    @Override
    public long count() {
        return insurances.size();
    }
    
    @Override
    public void delete(Insurance entity) {
        if (entity.getId() != null) {
            insurances.remove(entity.getId());
        }
    }
    
    @Override
    public void deleteAllById(Iterable<? extends UUID> ids) {
        ids.forEach(this::deleteById);
    }
    
    @Override
    public void deleteAll(Iterable<? extends Insurance> entities) {
        entities.forEach(this::delete);
    }
    
    @Override
    public void deleteAll() {
        insurances.clear();
    }
    
    @Override
    public org.springframework.data.domain.Page<Insurance> findAll(org.springframework.data.domain.Pageable pageable) {
        List<Insurance> pageContent = findAll().stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        
        return new org.springframework.data.domain.PageImpl<>(
            pageContent,
            pageable,
            count()
        );
    }
    
    @Override
    public void flush() {
        // No-op for in-memory
    }
    
    @Override
    public <S extends Insurance> S saveAndFlush(S entity) {
        return save(entity);
    }
    
    @Override
    public <S extends Insurance> List<S> saveAllAndFlush(Iterable<S> entities) {
        return saveAll(entities);
    }
    
    @Override
    public void deleteAllInBatch(Iterable<Insurance> entities) {
        deleteAll(entities);
    }
    
    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> ids) {
        deleteAllById(ids);
    }
    
    @Override
    public void deleteAllInBatch() {
        deleteAll();
    }
    
    @Override
    public Insurance getOne(UUID id) {
        return findById(id).orElse(null);
    }
    
    @Override
    public Insurance getById(UUID id) {
        return findById(id).orElse(null);
    }
    
    @Override
    public Insurance getReferenceById(UUID id) {
        return findById(id).orElse(null);
    }
    
    @Override
    public <S extends Insurance> Optional<S> findOne(org.springframework.data.domain.Example<S> example) {
        // Simplified implementation
        return Optional.empty();
    }
    
    @Override
    public <S extends Insurance> List<S> findAll(org.springframework.data.domain.Example<S> example) {
        return List.of();
    }
    
    @Override
    public <S extends Insurance> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) {
        return List.of();
    }
    
    @Override
    public <S extends Insurance> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) {
        return new org.springframework.data.domain.PageImpl<>(List.of(), pageable, 0);
    }
    
    @Override
    public <S extends Insurance> long count(org.springframework.data.domain.Example<S> example) {
        return 0;
    }
    
    @Override
    public <S extends Insurance> boolean exists(org.springframework.data.domain.Example<S> example) {
        return false;
    }
    
    @Override
    public <S extends Insurance, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.domain.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
