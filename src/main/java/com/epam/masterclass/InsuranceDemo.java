package com.epam.masterclass;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.epam.masterclass.controller.InsuranceController;
import com.epam.masterclass.controller.impl.InsuranceControllerImpl;
import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InMemoryInsuranceRepository;
import com.epam.masterclass.repository.InsuranceRepository;
import com.epam.masterclass.service.InsuranceService;
import com.epam.masterclass.service.impl.InsuranceServiceImpl;

/**
 * Demo class for Insurance CRUD operations.
 */
public class InsuranceDemo {

    public static void main(String[] args) {
        // Initialize components
        InsuranceRepository repository = new InMemoryInsuranceRepository();
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
        Insurance createdLife = controller.createInsurance(lifeInsurance);
        System.out.println("Created: " + createdLife);
        
        Insurance createdHealth = controller.createInsurance(healthInsurance);
        System.out.println("Created: " + createdHealth);
        
        Insurance createdCar = controller.createInsurance(carInsurance);
        System.out.println("Created: " + createdCar);
        
        // Demo: Get all insurances
        System.out.println("\n=== Getting All Insurances ===");
        List<Insurance> allInsurances = controller.getAllInsurances();
        allInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Get insurance by ID
        System.out.println("\n=== Getting Insurance by ID ===");
        Insurance foundInsurance = controller.getInsuranceById(createdLife.getId());
        System.out.println("Found: " + foundInsurance);
        
        // Demo: Get insurance by policy number
        System.out.println("\n=== Getting Insurance by Policy Number ===");
        Insurance foundByPolicy = controller.getInsuranceByPolicyNumber("H-67890");
        System.out.println("Found by policy number: " + foundByPolicy);
        
        // Demo: Get insurances by type
        System.out.println("\n=== Getting Insurances by Type ===");
        List<Insurance> healthInsurances = controller.getInsurancesByType("HEALTH");
        healthInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Get insurances by insured person ID
        System.out.println("\n=== Getting Insurances by Insured Person ID ===");
        List<Insurance> personInsurances = controller.getInsurancesByInsuredPersonId(lifeInsurance.getInsuredPersonId());
        System.out.println("Found " + personInsurances.size() + " insurances for person:");
        personInsurances.forEach(insurance -> System.out.println(insurance));
        
        // Demo: Update insurance
        System.out.println("\n=== Updating Insurance ===");
        createdHealth.setPremiumAmount(600.0);
        createdHealth.setStatus("UPDATED");
        Insurance updatedInsurance = controller.updateInsurance(createdHealth.getId(), createdHealth);
        System.out.println("Updated: " + updatedInsurance);
        
        // Demo: Delete insurance
        System.out.println("\n=== Deleting Insurance ===");
        boolean deleted = controller.deleteInsurance(createdCar.getId());
        System.out.println("Insurance deleted: " + deleted);
        
        // Verify deletion by listing all remaining insurances
        System.out.println("\n=== Remaining Insurances After Deletion ===");
        allInsurances = controller.getAllInsurances();
        allInsurances.forEach(insurance -> System.out.println(insurance));
    }
}
