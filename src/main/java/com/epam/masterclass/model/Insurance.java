package com.epam.masterclass.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Entity representing an insurance policy.
 */
@Entity
@Table(name = "insurances")
public class Insurance {
    
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @NotBlank(message = "Policy number is required")
    @Column(name = "policy_number", unique = true, nullable = false)
    private String policyNumber;
    
    @NotBlank(message = "Insurance type is required")
    @Column(name = "type")
    private String type;
    
    @Positive(message = "Premium amount must be positive")
    @Column(name = "premium_amount")
    private double premiumAmount;
    
    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "insured_person_id")
    private UUID insuredPersonId;
    
    @Column(name = "status")
    private String status;
    
    // Default constructor
    public Insurance() {
        this.id = UUID.randomUUID();
    }
    
    // Constructor with fields
    public Insurance(String policyNumber, String type, double premiumAmount, 
                    LocalDate startDate, LocalDate endDate, UUID insuredPersonId, String status) {
        this.id = UUID.randomUUID();
        this.policyNumber = policyNumber;
        this.type = type;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insuredPersonId = insuredPersonId;
        this.status = status;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public UUID getInsuredPersonId() {
        return insuredPersonId;
    }

    public void setInsuredPersonId(UUID insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Insurance{" +
               "id=" + id +
               ", policyNumber='" + policyNumber + '\'' +
               ", type='" + type + '\'' +
               ", premiumAmount=" + premiumAmount +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", insuredPersonId=" + insuredPersonId +
               ", status='" + status + '\'' +
               '}';
    }
}
