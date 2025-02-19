package com.epam.masterclass.service;

import com.epam.masterclass.model.TestRide;
import com.epam.masterclass.repository.TestRideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestRideServiceTest {

    @Mock
    private TestRideRepository repository;

    @InjectMocks
    private TestRideService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Prepare
        TestRide testRide1 = new TestRide();
        TestRide testRide2 = new TestRide();
        List<TestRide> testRides = Arrays.asList(testRide1, testRide2);
        when(repository.findAll()).thenReturn(testRides);

        // Act
        List<TestRide> result = service.findAll();

        // Check
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Prepare
        TestRide testRide = new TestRide();
        when(repository.save(testRide)).thenReturn(testRide);

        // Act
        TestRide result = service.save(testRide);

        // Check
        assertEquals(testRide, result);
        verify(repository, times(1)).save(testRide);
    }

    @Test
    void testBookRide() {
        // Prepare
        TestRide testRide = new TestRide();
        when(repository.save(testRide)).thenReturn(testRide);

        // Act
        TestRide result = service.bookRide(testRide);

        // Check
        assertEquals(testRide, result);
        verify(repository, times(1)).save(testRide);
    }

    @Test
    void testCancelBooking() {
        // Prepare
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        // Act
        service.cancelBooking(id);

        // Check
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testListRidesForCustomer() {
        // Prepare
        Long customerId = 1L;
        TestRide testRide1 = new TestRide();
        TestRide testRide2 = new TestRide();
        List<TestRide> testRides = Arrays.asList(testRide1, testRide2);
        when(repository.findByCustomerId(customerId)).thenReturn(testRides);

        // Act
        List<TestRide> result = service.listRidesForCustomer(customerId);

        // Check
        assertEquals(2, result.size());
        verify(repository, times(1)).findByCustomerId(customerId);
    }

    // Additional test methods for other business logic methods
}
