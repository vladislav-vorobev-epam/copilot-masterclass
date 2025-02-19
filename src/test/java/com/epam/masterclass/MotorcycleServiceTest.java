package com.epam.masterclass;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.masterclass.service.MotorcycleService;

@SpringBootTest
public class MotorcycleServiceTest {

    @Autowired
    private MotorcycleService motorcycleService;

    @Test
    public void testFindAll() {
        assertNotNull(motorcycleService.findAll());
    }
}