package com.epam.masterclass.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.epam.masterclass.model.Insurance;

/**
 * Custom repository interface for Insurance entity.
 * This is simpler than using the full JpaRepository for our demo purposes.
 */
@Repository
public interface InsuranceRepositoryCustom {
    
    /**
     * Saves a new insurance or updates an existing one.
     *
     * @param insurance the insurance to save
     * @return the saved insurance
     */
    Insurance save(Insurance insurance);
    
    /**
     * Finds an insurance by its ID.
     *
     * @param id the ID to search for
     * @return an Optional containing the found insurance, or empty if not found
     */
    Optional<Insurance> findById(UUID id);
    
    /**
     * Finds an insurance by its policy number.
     *
     * @param policyNumber the policy number to search for
     * @return an Optional containing the found insurance, or empty if not found
     */
    Optional<Insurance> findByPolicyNumber(String policyNumber);
    
    /**
     * Gets all insurances.
     *
     * @return a list of all insurances
     */
    List<Insurance> findAll();
    
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
    
    /**
     * Deletes an insurance by its ID.
     *
     * @param id the ID of the insurance to delete
     */
    void deleteById(UUID id);
    
    /**
     * Checks if an insurance exists by ID.
     *
     * @param id the ID to check
     * @return true if the insurance exists, false otherwise
     */
    boolean existsById(UUID id);
}
