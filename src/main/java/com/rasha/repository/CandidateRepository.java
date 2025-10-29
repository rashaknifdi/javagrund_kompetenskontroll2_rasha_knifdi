package com.rasha.repository;

import com.rasha.model.Candidate;

import java.util.List;

/**
 * Abstrakt gränssnitt för datalagring av kandidater.
 * Gör att vi kan byta lagringsmetod utan att ändra affärslogik – följer Dependency Inversion Principle (DIP).
 */


public interface CandidateRepository {
    void save(Candidate candidate);
    void delete(Candidate candidate);
    List<Candidate> findAll();

}
