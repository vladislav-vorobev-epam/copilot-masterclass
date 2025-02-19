package com.epam.masterclass.controller;

import com.epam.masterclass.model.Motorcycle;
import com.epam.masterclass.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {
    @Autowired
    private MotorcycleService service;

    @GetMapping
    public List<Motorcycle> getAllMotorcycles() {
        return service.findAll();
    }

    @PostMapping
    public Motorcycle createMotorcycle(@RequestBody Motorcycle motorcycle) {
        return service.save(motorcycle);
    }

    // Other endpoints
}