package com.rasha.service.filter;

import com.rasha.model.Candidate;
import com.rasha.util.LoggerUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filtrerar kandidater baserat på antal år erfarenhet.
 * Följer SRP genom att ha ett avgränsat ansvar.
 * Följer OCP genom att implementera CandidateFilter.
 */

public class ExperienceFilter implements CandidateFilter {
    private final int experienceYears;
    public ExperienceFilter(int minYears) {
        this.experienceYears = minYears;
    }
    @Override
    public List<Candidate> apply(List<Candidate> candidates) {
        List<Candidate> result = candidates.stream()
                .filter(c -> c.getYearsOfExperience() >= experienceYears)
                .collect(Collectors.toList());

        LoggerUtil.logInfo("→ ExperienceFilter: filtrerar kandidater med minst " + experienceYears + " års erfarenhet.");
        return result;
    }
}
