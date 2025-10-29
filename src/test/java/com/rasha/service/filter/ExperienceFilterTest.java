package com.rasha.service.filter;

import com.rasha.model.Candidate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExperienceFilterTest {
    @Test
    void shouldFilterCandidatesWithEnoughExperience() {
        List<Candidate> input = List.of(
                new Candidate("Rasha", "Knifdi", 36, "IT", 2),
                new Candidate("Sayer", "Fadel", 45, "IT", 5)
        );

        ExperienceFilter filter = new ExperienceFilter(3);
        List<Candidate> result = filter.apply(input);

        assertEquals(1, result.size());
        assertEquals("Sayer", result.get(0).getFirstName());
    }
}
