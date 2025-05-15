package com.epam.masterclass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InMemoryInsuranceRepository;
import com.epam.masterclass.repository.InsuranceRepository;
import com.epam.masterclass.service.impl.InsuranceServiceImpl;

/**
 * Test for InsuranceService.
 */
public class InsuranceServiceTest {

    private InsuranceService insuranceService;
    private InsuranceRepository insuranceRepository;
    private UUID testPersonId;
    
    @BeforeEach
    public void setUp() {
        insuranceRepository = new InMemoryInsuranceRepository();
        insuranceService = new InsuranceServiceImpl(insuranceRepository);
        testPersonId = UUID.randomUUID();
    }
    
    @Test
    public void testCreateInsurance() {
        // Given
        Insurance insurance = createTestInsurance("TEST-12345", "LIFE", testPersonId);
        
        // When
        Insurance created = insuranceService.createInsurance(insurance);
        
        // Then
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("TEST-12345", created.getPolicyNumber());
    }
    
    @Test
    public void testGetInsuranceById() {
        // Given
        Insurance insurance = createTestInsurance("TEST-12345", "LIFE", testPersonId);
        Insurance created = insuranceService.createInsurance(insurance);
        
        // When
        Optional<Insurance> found = insuranceService.getInsuranceById(created.getId());
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("TEST-12345", found.get().getPolicyNumber());
    }
    
    @Test
    public void testGetInsuranceByPolicyNumber() {
        // Given
        Insurance insurance = createTestInsurance("UNIQUE-12345", "HEALTH", testPersonId);
        insuranceService.createInsurance(insurance);
        
        // When
        Optional<Insurance> found = insuranceService.getInsuranceByPolicyNumber("UNIQUE-12345");
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("HEALTH", found.get().getType());
    }
    
    @Test
    public void testGetAllInsurances() {
        // Given
        insuranceService.createInsurance(createTestInsurance("POL-1", "LIFE", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-2", "HEALTH", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-3", "CAR", UUID.randomUUID()));
        
        // When
        List<Insurance> insurances = insuranceService.getAllInsurances();
        
        // Then
        assertEquals(3, insurances.size());
    }
    
    @Test
    public void testGetInsurancesByType() {
        // Given
        insuranceService.createInsurance(createTestInsurance("POL-1", "LIFE", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-2", "HEALTH", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-3", "HEALTH", UUID.randomUUID()));
        
        // When
        List<Insurance> healthInsurances = insuranceService.getInsurancesByType("HEALTH");
        
        // Then
        assertEquals(2, healthInsurances.size());
        assertTrue(healthInsurances.stream().allMatch(i -> "HEALTH".equals(i.getType())));
    }
    
    @Test
    public void testGetInsurancesByInsuredPersonId() {
        // Given
        insuranceService.createInsurance(createTestInsurance("POL-1", "LIFE", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-2", "HEALTH", testPersonId));
        insuranceService.createInsurance(createTestInsurance("POL-3", "CAR", UUID.randomUUID()));
        
        // When
        List<Insurance> personInsurances = insuranceService.getInsurancesByInsuredPersonId(testPersonId);
        
        // Then
        assertEquals(2, personInsurances.size());
    }
    
    @Test
    public void testUpdateInsurance() {
        // Given
        Insurance insurance = createTestInsurance("POL-1", "LIFE", testPersonId);
        Insurance created = insuranceService.createInsurance(insurance);
        
        // When
        created.setPremiumAmount(1500.0);
        created.setStatus("UPDATED");
        Insurance updated = insuranceService.updateInsurance(created);
        
        // Then
        assertEquals(1500.0, updated.getPremiumAmount());
        assertEquals("UPDATED", updated.getStatus());
    }
    
    @Test
    public void testUpdateInsuranceNotFound() {
        // Given
        Insurance insurance = createTestInsurance("POL-1", "LIFE", testPersonId);
        insurance.setId(UUID.randomUUID()); // A non-existent ID
        
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            insuranceService.updateInsurance(insurance);
        });
    }
    
    @Test
    public void testDeleteInsurance() {
        // Given
        Insurance insurance = createTestInsurance("POL-1", "LIFE", testPersonId);
        Insurance created = insuranceService.createInsurance(insurance);
        
        // When
        boolean deleted = insuranceService.deleteInsurance(created.getId());
        
        // Then
        assertTrue(deleted);
        assertTrue(insuranceService.getInsuranceById(created.getId()).isEmpty());
    }
    
    @Test
    public void testDeleteInsuranceNotFound() {
        // When
        boolean deleted = insuranceService.deleteInsurance(UUID.randomUUID());
        
        // Then
        assertFalse(deleted);
    }
    
    private Insurance createTestInsurance(String policyNumber, String type, UUID insuredPersonId) {
        return new Insurance(
            policyNumber,
            type,
            1000.0,
            LocalDate.now(),
            LocalDate.now().plusYears(1),
            insuredPersonId,
            "ACTIVE"
        );
    }
}
