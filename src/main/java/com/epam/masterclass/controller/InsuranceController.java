package com.epam.masterclass.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.masterclass.model.Insurance;

/**
 * REST Controller interface for Insurance entity.
 * Defines endpoints for handling CRUD operations.
 */
@RequestMapping("/api/insurances")
public interface InsuranceController {
    
    /**
     * Creates a new insurance.
     * 
     * @param insurance the insurance to create
     * @return the created insurance
     */
    @PostMapping
    ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance);
    
    /**
     * Retrieves an insurance by its ID.
     * 
     * @param id the ID of the insurance to retrieve
     * @return the insurance with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    ResponseEntity<Insurance> getInsuranceById(@PathVariable UUID id);
    
    /**
     * Retrieves an insurance by its policy number.
     * 
     * @param policyNumber the policy number to search for
     * @return the insurance with the specified policy number, or 404 if not found
     */
    @GetMapping("/policy/{policyNumber}")
    ResponseEntity<Insurance> getInsuranceByPolicyNumber(@PathVariable String policyNumber);
    
    /**
     * Retrieves all insurances.
     * 
     * @return a list of all insurances
     */
    @GetMapping
    ResponseEntity<List<Insurance>> getAllInsurances();
    
    /**
     * Retrieves all insurances of a specific type.
     * 
     * @param type the type of insurance to search for
     * @return a list of insurances matching the specified type
     */
    @GetMapping("/type/{type}")
    ResponseEntity<List<Insurance>> getInsurancesByType(@PathVariable String type);
    
    /**
     * Retrieves all insurances for a specific insured person.
     * 
     * @param insuredPersonId the ID of the insured person
     * @return a response entity containing a list of insurances for the specified insured person
     */
    @GetMapping("/insuredPerson/{insuredPersonId}")
    ResponseEntity<List<Insurance>> getInsurancesByInsuredPersonId(@PathVariable UUID insuredPersonId);
    
    /**
     * Updates an existing insurance.
     * 
     * @param id the ID of the insurance to update
     * @param insurance the insurance with updated information
     * @return a response entity containing the updated insurance, or 404 if not found
     */
    @PostMapping("/{id}")
    ResponseEntity<Insurance> updateInsurance(@PathVariable UUID id, @RequestBody Insurance insurance);
    
    /**
     * Deletes an insurance by its ID.
     * 
     * @param id the ID of the insurance to delete
     * @return a response entity with no content if successful, or appropriate error status
     */
    @GetMapping("/{id}/delete")
    ResponseEntity<Void> deleteInsurance(@PathVariable UUID id);
}
