package com.epam.masterclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.masterclass.model.Customer;
import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.service.TestRideService;

@RestController
@RequestMapping("/testrides")
public class TestRideController {

    @Autowired
    private TestRideService testRideService;

    @PostMapping("/book")
    public TestRide bookRide(@RequestBody TestRide testRide) {
        return testRideService.bookRide(testRide);
    }

    @DeleteMapping("/{id}/cancel")
    public void cancelBooking(@PathVariable Long id) {
        testRideService.cancelBooking(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<TestRide> listBookingsForCustomer(@PathVariable Customer customer) {
        return testRideService.listBookingsForCustomer(customer);
    }

    // Other endpoints
}