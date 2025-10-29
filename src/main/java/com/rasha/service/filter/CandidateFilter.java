package com.rasha.service.filter;

import com.rasha.model.Candidate;
import java.util.List;

/**
 * Interface för filtrering och sortering av kandidater.
 * Nya filter kan läggas till utan att ändra befintlig kod – följer Open/Closed Principle (OCP).
 */

public interface CandidateFilter {
    List<Candidate> apply(List<Candidate> candidates);
}