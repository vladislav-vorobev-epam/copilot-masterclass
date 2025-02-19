package com.epam.masterclass.service;

import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.repository.TestRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRideService {
    @Autowired
    private TestRideRepository repository;

    public List<TestRide> findAll() {
        return repository.findAll();
    }

    public TestRide save(TestRide testRide) {
        return repository.save(testRide);
    }

    public TestRide bookRide(TestRide testRide) {
        // Business logic for booking a ride
        return repository.save(testRide);
    }

    public void cancelBooking(Long id) {
        // Business logic for canceling a booking
        repository.deleteById(id);
    }

    public List<TestRide> listRidesForCustomer(Long customerId) {
        // Business logic for listing rides for a specific customer
        return repository.findByCustomerId(customerId);
    }

    // Other business logic methods
}
