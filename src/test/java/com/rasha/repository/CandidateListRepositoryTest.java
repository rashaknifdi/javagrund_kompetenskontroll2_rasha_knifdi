package com.rasha.repository;

import com.rasha.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CandidateListRepositoryTest {

    private CandidateListRepository repository = new CandidateListRepository();

    @Test
    void shouldSaveCandidate() {
        Candidate candidate = new Candidate("Rasha", "Knifdi", 36, "IT", 5);
        repository.save(candidate);

        List<Candidate> result = repository.findAll();
        assertEquals(1, result.size());
        assertEquals("Rasha", result.get(0).getFirstName());
    }

    @Test
    void shouldDeleteCandidate() {
        Candidate candidate = new Candidate("Sayer", "Fadel", 45, "Finance", 3);
        repository.save(candidate);
        repository.delete(candidate);

        List<Candidate> result = repository.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnUnmodifiableList() {
        Candidate candidate = new Candidate("Reem", "Mohammad", 38, "HR", 7);
        repository.save(candidate);

        List<Candidate> result = repository.findAll();
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Candidate("Test", "Person", 20, "IT", 1)));
    }
}
