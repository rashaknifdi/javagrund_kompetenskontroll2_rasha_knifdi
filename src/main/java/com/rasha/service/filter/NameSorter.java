package com.rasha.service.filter;

import com.rasha.model.Candidate;
import com.rasha.util.LoggerUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorterar kandidater alfabetiskt efter förnamn.
 * Följer SRP genom att endast hantera sortering.
 * Följer OCP genom att vara utbytbar via CandidateFilter.
 */

public class NameSorter implements CandidateFilter {
    @Override
    public List<Candidate> apply(List<Candidate> candidates) {
        List<Candidate> result = candidates.stream()
                .sorted((c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName()))
                .collect(Collectors.toList());
        LoggerUtil.logInfo("→ NameSorter: sorterar kandidater alfabetiskt efter förnamn.");
        return result;
    }
}
