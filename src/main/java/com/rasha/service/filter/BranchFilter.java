package com.rasha.service.filter;

import com.rasha.model.Candidate;
import com.rasha.util.LoggerUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filtrerar kandidater baserat på bransch.
 * Har ett tydligt ansvar – följer SRP.
 * Kan läggas till utan att påverka annan kod – följer OCP.
 */
public class BranchFilter implements CandidateFilter {
    private final String branch;
    public BranchFilter(String branch) {
        this.branch = branch;
    }
    @Override
    public List<Candidate> apply(List<Candidate> candidates) {
        List<Candidate> result = candidates.stream()
                .filter(c -> c.getBranch().equalsIgnoreCase(branch))
                .collect(Collectors.toList());

        LoggerUtil.logInfo("→ BranchFilter: filtrerar kandidater där branch = '" + branch + "'.");
        return result;
    }
}
