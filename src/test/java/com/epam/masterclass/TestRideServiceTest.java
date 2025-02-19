package com.epam.masterclass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.masterclass.model.Customer;
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
    public void testBookRide() {
        // Arrange
        TestRide testRide = new TestRide();
        when(testRideRepository.save(testRide)).thenReturn(testRide);

        // Act
        TestRide bookedTestRide = testRideService.bookRide(testRide);

        // Assert
        assertNotNull(bookedTestRide);
        assertEquals(testRide, bookedTestRide);
        verify(testRideRepository).save(testRide);
    }

    @Test
    public void testCancelBooking() {
        // Arrange
        TestRide testRide = new TestRide();
        when(testRideRepository.save(testRide)).thenReturn(testRide);
        testRideService.bookRide(testRide);

        // Act
        testRideService.cancelBooking(testRide.getId());

        // Assert
        verify(testRideRepository).deleteById(testRide.getId());
    }

    @Test
    public void testListBookingsForCustomer() {
        // Arrange
        List<TestRide> expectedTestRides = new ArrayList<>();
        when(testRideRepository.findByCustomerId(1L)).thenReturn(expectedTestRides);

        // Act
        Customer customer = new Customer(1L, "John Doe", "test@test.com");
        List<TestRide> actualTestRides = testRideService.listBookingsForCustomer(customer);

        // Assert
        assertNotNull(actualTestRides);
        assertEquals(expectedTestRides, actualTestRides);
    }
}