package com.rasha.service.filter;

import com.rasha.model.Candidate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BranchFilterTest {

    @Test
    void shouldFilterCandidatesByBranch() {
        List<Candidate> input = List.of(
                new Candidate("Rasha", "Knifdi", 36, "IT", 5),
                new Candidate("Asmahan", "Fadel", 26, "Finance", 3)
        );

        BranchFilter filter = new BranchFilter("IT");
        List<Candidate> result = filter.apply(input);

        assertEquals(1, result.size());
        assertEquals("Rasha", result.get(0).getFirstName());
    }
}