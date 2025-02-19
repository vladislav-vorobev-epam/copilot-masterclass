package com.epam.masterclass.service;

import com.epam.masterclass.model.Motorcycle;
import com.epam.masterclass.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository repository;

    public List<Motorcycle> findAll() {
        return repository.findAll();
    }

    public Motorcycle save(Motorcycle motorcycle) {
        return repository.save(motorcycle);
    }

    // Other business logic methods
}