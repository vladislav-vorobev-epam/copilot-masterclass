package com.epam.masterclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.masterclass.model.TestRide;

public interface TestRideRepository extends JpaRepository<TestRide, Long> {
    List<TestRide> findByCustomerId(Long customerId);
}
