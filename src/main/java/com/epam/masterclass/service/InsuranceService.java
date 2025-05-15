package com.epam.masterclass.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.epam.masterclass.model.Insurance;

/**
 * Service interface for Insurance entity.
 */
public interface InsuranceService {
    
    /**
     * Creates a new insurance.
     * 
     * @param insurance the insurance to create
     * @return the created insurance
     */
    Insurance createInsurance(Insurance insurance);
    
    /**
     * Retrieves an insurance by its ID.
     * 
     * @param id the ID of the insurance to retrieve
     * @return an Optional containing the insurance, or empty if not found
     */
    Optional<Insurance> getInsuranceById(UUID id);
    
    /**
     * Retrieves an insurance by its policy number.
     * 
     * @param policyNumber the policy number to search for
     * @return an Optional containing the insurance, or empty if not found
     */
    Optional<Insurance> getInsuranceByPolicyNumber(String policyNumber);
    
    /**
     * Retrieves all insurances.
     * 
     * @return a list of all insurances
     */
    List<Insurance> getAllInsurances();
    
    /**
     * Retrieves all insurances of a specific type.
     * 
     * @param type the type of insurance to search for
     * @return a list of insurances matching the specified type
     */
    List<Insurance> getInsurancesByType(String type);
    
    /**
     * Retrieves all insurances for a specific insured person.
     * 
     * @param insuredPersonId the ID of the insured person
     * @return a list of insurances for the specified insured person
     */
    List<Insurance> getInsurancesByInsuredPersonId(UUID insuredPersonId);
    
    /**
     * Updates an existing insurance.
     * 
     * @param insurance the insurance with updated information
     * @return the updated insurance
     * @throws IllegalArgumentException if the insurance doesn't exist
     */
    Insurance updateInsurance(Insurance insurance);
    
    /**
     * Deletes an insurance by its ID.
     * 
     * @param id the ID of the insurance to delete
     * 
     */
    void deleteInsurance(UUID id);
}
