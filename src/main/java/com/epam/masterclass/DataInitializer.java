package com.epam.masterclass;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.repository.InsuranceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data initializer for development environment.
 * Creates sample insurance policies on application startup.
 */
@Configuration
@Profile("!test") // Skip this initialization during tests
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(InsuranceRepository insuranceRepository) {
        return args -> {
            // Create sample data only if repository is empty
            if (insuranceRepository.count() == 0) {
                System.out.println("Initializing sample insurance data...");
                
                // Create sample insurances with different types
                createInsurance(insuranceRepository, "POL-12345", "Health", 1250.00, 
                        LocalDate.now(), LocalDate.now().plusYears(1), "Active");
                
                createInsurance(insuranceRepository, "POL-23456", "Auto", 750.50, 
                        LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(10), "Active");
                
                createInsurance(insuranceRepository, "POL-34567", "Home", 950.75, 
                        LocalDate.now().minusDays(15), LocalDate.now().plusYears(1).minusDays(15), "Active");
                
                createInsurance(insuranceRepository, "POL-45678", "Life", 2500.00, 
                        LocalDate.now().minusMonths(6), LocalDate.now().plusYears(10).minusMonths(6), "Active");
                
                createInsurance(insuranceRepository, "POL-56789", "Travel", 150.25, 
                        LocalDate.now().plusDays(10), LocalDate.now().plusDays(40), "Pending");
                
                createInsurance(insuranceRepository, "POL-67890", "Health", 1100.00, 
                        LocalDate.now().minusYears(1), LocalDate.now(), "Expired");
                
                System.out.println("Sample data initialization complete.");
            }
        };
    }
    
    private void createInsurance(InsuranceRepository repository, String policyNumber, String type, 
                                 double premium, LocalDate startDate, LocalDate endDate, String status) {
        Insurance insurance = new Insurance();
        insurance.setId(UUID.randomUUID());
        insurance.setPolicyNumber(policyNumber);
        insurance.setType(type);
        insurance.setPremiumAmount(premium);
        insurance.setStartDate(startDate);
        insurance.setEndDate(endDate);
        insurance.setInsuredPersonId(UUID.randomUUID());
        insurance.setStatus(status);
        
        repository.save(insurance);
    }
}
