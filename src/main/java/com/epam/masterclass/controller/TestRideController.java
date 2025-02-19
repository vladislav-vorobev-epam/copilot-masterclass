package com.epam.masterclass.controller;

import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.service.TestRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testrides")
public class TestRideController {
    @Autowired
    private TestRideService service;

    @GetMapping
    public List<TestRide> getAllTestRides() {
        return service.findAll();
    }

    @PostMapping
    public TestRide createTestRide(@RequestBody TestRide testRide) {
        return service.save(testRide);
    }

    // Other endpoints
}