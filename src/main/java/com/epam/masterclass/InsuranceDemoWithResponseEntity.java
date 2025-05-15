// filepath: /Users/Vladislav_Vorobev/IdeaProjects/copilot-masterclass/src/main/java/com/epam/masterclass/InsuranceDemo.java
package com.epam.masterclass;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.epam.masterclass.controller.InsuranceController;
import com.epam.masterclass.controller.impl.InsuranceControllerImpl;
import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.impl.InMemoryInsuranceRepositoryImpl;
import com.epam.masterclass.service.InsuranceService;
import com.epam.masterclass.service.impl.InsuranceServiceImpl;

/**
 * Demo class for Insurance CRUD operations.
 * This class demonstrates the usage of the Insurance API.
 */
public class InsuranceDemo {

    public static void main(String[] args) {
        // Initialize components (Note: in a Spring application, these would be autowired)
        InMemoryInsuranceRepositoryImpl repository = new InMemoryInsuranceRepositoryImpl();
        InsuranceService service = new InsuranceServiceImpl(repository);
        InsuranceController controller = new InsuranceControllerImpl(service);
        
        // Create a few sample insurances
        Insurance lifeInsurance = new Insurance(
                "L-12345",
                "LIFE",
                1000.0,
                LocalDate.now(),
                LocalDate.now().plusYears(10),
                UUID.randomUUID(),
                "ACTIVE"
        );
        
        Insurance healthInsurance = new Insurance(
                "H-67890",
                "HEALTH",
                500.0,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                lifeInsurance.getInsuredPersonId(), // Same person
                "ACTIVE"
        );
        
        Insurance carInsurance = new Insurance(
                "C-54321",
                "CAR",
                750.0,
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                UUID.randomUUID(),
                "ACTIVE"
        );
        
        // Demo: Create insurances
        System.out.println("=== Creating Insurances ===");
        ResponseEntity<Insurance> createdLifeResponse = controller.createInsurance(lifeInsurance);
        Insurance createdLife = createdLifeResponse.getBody();
        System.out.println("Created: " + createdLife);
        
        ResponseEntity<Insurance> createdHealthResponse = controller.createInsurance(healthInsurance);
        Insurance createdHealth = createdHealthResponse.getBody();
        System.out.println("Created: " + createdHealth);
        
        ResponseEntity<Insurance> createdCarResponse = controller.createInsurance(carInsurance);
        Insurance createdCar = createdCarResponse.getBody();
        System.out.println("Created: " + createdCar);
        
        // Demo: Get all insurances
        System.out.println("\n=== Getting All Insurances ===");
        ResponseEntity<List<Insurance>> allInsurancesResponse = controller.getAllInsurances();
        List<Insurance> allInsurances = allInsurancesResponse.getBody();
        allInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Get insurance by ID
        System.out.println("\n=== Getting Insurance by ID ===");
        ResponseEntity<Insurance> foundInsuranceResponse = controller.getInsuranceById(createdLife.getId());
        Insurance foundInsurance = foundInsuranceResponse.getBody();
        System.out.println("Found: " + foundInsurance);
        
        // Demo: Get insurance by policy number
        System.out.println("\n=== Getting Insurance by Policy Number ===");
        ResponseEntity<Insurance> foundByPolicyResponse = controller.getInsuranceByPolicyNumber("H-67890");
        Insurance foundByPolicy = foundByPolicyResponse.getBody();
        System.out.println("Found by policy number: " + foundByPolicy);
        
        // Demo: Get insurances by type
        System.out.println("\n=== Getting Insurances by Type ===");
        ResponseEntity<List<Insurance>> healthInsurancesResponse = controller.getInsurancesByType("HEALTH");
        List<Insurance> healthInsurances = healthInsurancesResponse.getBody();
        healthInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Get insurances by insured person ID
        System.out.println("\n=== Getting Insurances by Insured Person ID ===");
        ResponseEntity<List<Insurance>> personInsurancesResponse = controller.getInsurancesByInsuredPersonId(lifeInsurance.getInsuredPersonId());
        List<Insurance> personInsurances = personInsurancesResponse.getBody();
        System.out.println("Found " + personInsurances.size() + " insurances for person:");
        personInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Update insurance
        System.out.println("\n=== Updating Insurance ===");
        createdHealth.setPremiumAmount(600.0);
        createdHealth.setStatus("UPDATED");
        ResponseEntity<Insurance> updatedInsuranceResponse = controller.updateInsurance(createdHealth.getId(), createdHealth);
        Insurance updatedInsurance = updatedInsuranceResponse.getBody();
        System.out.println("Updated: " + updatedInsurance);
        
        // Demo: Delete insurance
        System.out.println("\n=== Deleting Insurance ===");
        ResponseEntity<Void> deleteResponse = controller.deleteInsurance(createdCar.getId());
        boolean deleted = deleteResponse.getStatusCode().is2xxSuccessful();
        System.out.println("Insurance deleted: " + deleted);
        
        // Verify deletion by listing all remaining insurances
        System.out.println("\n=== Remaining Insurances After Deletion ===");
        allInsurancesResponse = controller.getAllInsurances();
        allInsurances = allInsurancesResponse.getBody();
        allInsurances.forEach(insurance -> System.out.println(insurance));
    }
}
