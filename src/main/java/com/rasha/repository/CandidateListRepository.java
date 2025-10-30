package com.rasha.repository;

import com.rasha.model.Candidate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lagrar kandidater i en intern lista.
 * Implementerar CandidateRepository och håller lagring separat från logik – följer SRP och DIP.
 */

public class CandidateListRepository implements CandidateRepository{
    private final List<Candidate> candidates = new ArrayList<>();
    @Override
    public void save(Candidate candidate) {
        candidates.add(candidate);
    }
    @Override
    public void delete(Candidate candidate) {
        candidates.removeIf(c ->
                        c.getFirstName().equalsIgnoreCase(candidate.getFirstName()) &&
                        c.getLastName().equalsIgnoreCase(candidate.getLastName())) ;

    }
    @Override
    public List<Candidate> findAll() {
        return Collections.unmodifiableList(candidates);
    }

}
