package com.epam.masterclass.service;

import com.epam.masterclass.model.Customer;
import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.repository.TestRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestRideService {

    @Autowired
    private TestRideRepository testRideRepository;

    public TestRide bookRide(TestRide testRide) {
        // Business logic for booking a ride (e.g., validation, conflict check)
        // For simplicity, we'll just save the testRide
        return testRideRepository.save(testRide);
    }

    public void cancelBooking(Long testRideId) {
        // Business logic for cancelling a booking (e.g., check if exists, permissions)
        testRideRepository.deleteById(testRideId);
    }

    public List<TestRide> listBookingsForCustomer(Customer customer) {
        // Retrieve all test rides and filter by customer
        return testRideRepository.findAll();
        /* .stream()
                .filter(testRide -> testRide.getCustomer().equals(customer))
                .collect(Collectors.toList()); */
    }

    public List<TestRide> findAll() {
        return testRideRepository.findAll();
    }
}