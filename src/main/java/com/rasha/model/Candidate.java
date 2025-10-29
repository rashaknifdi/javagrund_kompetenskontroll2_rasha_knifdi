package com.rasha.model;

import java.util.UUID;

/**
 * Representerar en kandidat med för- och efternamn, ålder, bransch och erfarenhet.
 * Har endast ansvar för att hålla data – följer Single Responsibility Principle (SRP).
 */


public class Candidate {
    private final String id;                  // Unik identifierare
    private String firstName;
    private String lastName;
    private int age;
    private String branch;
    private int yearsOfExperience;

    public Candidate(String firstName, String lastName, int age, String branch, int yearsOfExperience) {
        this.id = UUID.randomUUID().toString().substring(0, 8);// Automatisk ID-generering
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.branch = branch;
        this.yearsOfExperience = yearsOfExperience;
    }

    //<editor-fold desc="Getters">

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public String getBranch() {
        return branch;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return String.format(
                "👤 %s %s | Ålder: %d | Bransch: %s | Erfarenhet: %d år | ID: %s",
                firstName, lastName, age, branch, yearsOfExperience, id
        );
    }
}
