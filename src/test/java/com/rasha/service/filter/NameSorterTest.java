package com.rasha.service.filter;

import org.junit.jupiter.api.Test;
import com.rasha.model.Candidate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameSorterTest {
    @Test
    void shouldSortCandidatesByFirstNameAlphabetically() {

        List<Candidate> input = List.of(
                new Candidate("Rasha", "Knifdi", 36, "IT", 5),
                new Candidate("Mostafa", "Mohamad", 45, "IT", 5),
                new Candidate("Adam", "Samia", 26, "Finance", 3)
        );

        NameSorter sorter = new NameSorter();
        List<Candidate> result = sorter.apply(input);

        assertEquals("Adam", result.get(0).getFirstName());
        assertEquals("Mostafa", result.get(1).getFirstName());
        assertEquals("Rasha", result.get(2).getFirstName());

    }
}