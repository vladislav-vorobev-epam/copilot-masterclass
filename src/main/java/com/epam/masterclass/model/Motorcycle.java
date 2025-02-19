package com.epam.masterclass.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Column;

    @Entity
    public class Motorcycle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String model;

        @Column(name = "`year`")
        private int year;

        // Getters and Setters
    }