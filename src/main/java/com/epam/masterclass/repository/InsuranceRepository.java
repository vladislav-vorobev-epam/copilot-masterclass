package com.epam.masterclass.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.masterclass.model.Insurance;

/**
 * Repository interface for Insurance entity using Spring Data JPA.
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
    
    /**
     * Finds an insurance by its policy number.
     * 
     * @param policyNumber the policy number to search for
     * @return an Optional containing the found insurance, or empty if not found
     */
    Optional<Insurance> findByPolicyNumber(String policyNumber);
    
    /**
     * Finds all insurances by their type.
     * 
     * @param type the type to search for
     * @return a list of insurances matching the specified type
     */
    List<Insurance> findByType(String type);
    
    /**
     * Finds all insurances by the insured person's ID.
     * 
     * @param insuredPersonId the ID of the insured person
     * @return a list of insurances for the specified insured person
     */
    List<Insurance> findByInsuredPersonId(UUID insuredPersonId);
    
    // Note: No need to declare deleteById as it's inherited from JpaRepository
    // Note: No need for update method as save() in JpaRepository handles both inserts and updates
}
