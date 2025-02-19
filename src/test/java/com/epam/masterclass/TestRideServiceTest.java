package com.epam.masterclass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.repository.TestRideRepository;
import com.epam.masterclass.service.TestRideService;

@SpringBootTest
public class TestRideServiceTest {

    @Autowired
    private TestRideService testRideService;

    @MockBean
    private TestRideRepository testRideRepository;

    @Test
    public void testFindAll() {
        // Arrange
        List<TestRide> expectedTestRides = new ArrayList<>();
        when(testRideRepository.findAll()).thenReturn(expectedTestRides);

        // Act
        List<TestRide> actualTestRides = testRideService.findAll();

        // Assert
        assertNotNull(actualTestRides);
        assertEquals(expectedTestRides, actualTestRides);
    }

    @Test
    public void testSave() {
        // Arrange
        TestRide testRide = new TestRide();
        when(testRideRepository.save(testRide)).thenReturn(testRide);

        // Act
        TestRide savedTestRide = testRideService.save(testRide);

        // Assert
        assertNotNull(savedTestRide);
        assertEquals(testRide, savedTestRide);
    }

    
}