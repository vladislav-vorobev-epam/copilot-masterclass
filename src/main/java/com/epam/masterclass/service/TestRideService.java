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

    // Other business logic methods
}