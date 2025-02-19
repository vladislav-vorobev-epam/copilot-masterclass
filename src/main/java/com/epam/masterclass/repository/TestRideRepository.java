package com.epam.masterclass.repository;

import com.epam.masterclass.model.TestRide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRideRepository extends JpaRepository<TestRide, Long> {
}