package com.rasha.service;

import com.rasha.model.Candidate;
import com.rasha.repository.CandidateRepository;
import com.rasha.service.filter.CandidateFilter;
import com.rasha.util.LoggerUtil;
import java.util.List;

/**
 * Affärslogik för att hantera kandidater: lägga till, ta bort, filtrera, sortera.
 * Följer SRP genom att endast hantera logik.
 * Följer DIP genom att använda CandidateRepository och CandidateFilter som interface.
 */

public class CandidateService {
    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    // Lägg till kandidat med dubblettkontroll
    public void addCandidate(Candidate candidate) {
        if (candidateExists(candidate)) {
            LoggerUtil.logWarn("Dubblett upptäckt: " + candidate.getFullName());
            System.out.println("Kandidaten finns redan i systemet.");
            return;
        }
        repository.save(candidate);
        LoggerUtil.logInfo("Kandidat tillagd: " + candidate.getFullName());
    }

    // Kontrollera om kandidat redan finns
    public boolean candidateExists(Candidate candidate) {
        return repository.findAll().stream()
                .anyMatch(c ->
                        c.getFirstName().equalsIgnoreCase(candidate.getFirstName()) &&
                                c.getLastName().equalsIgnoreCase(candidate.getLastName()));
    }

    // Hämta alla kandidater
    public List<Candidate> getAllCandidates() {
        List<Candidate> result = repository.findAll();
        LoggerUtil.logInfo("Totalt antal kandidater hämtade: " + result.size());
        return result;
    }

    // Filtrera med strategi
    public List<Candidate> filter(CandidateFilter filter) {
        List<Candidate> result = filter.apply(repository.findAll());
        LoggerUtil.logInfo("Filtrering med " + filter.getClass().getSimpleName() +
                " klar – " + result.size() + " kandidater matchade.");
        return result;
    }

    // Sortera med strategi
    public List<Candidate> sort(CandidateFilter sorter) {
        List<Candidate> result = sorter.apply(repository.findAll());
        LoggerUtil.logInfo("Sortering med " + sorter.getClass().getSimpleName() +
                " klar – " + result.size() + " kandidater sorterade.");
        return result;
    }

    // Ta bort kandidat
    public void removeCandidate(Candidate candidate) {
        if (!candidateExists(candidate)) {
            LoggerUtil.logWarn(" kandidatuppgift saknas: " + candidate.getFullName() + " (" + candidate.getAge() + ")");
            System.out.println("Kandidaten finns inte i systemet.");
            return;
        }
        repository.delete(candidate);
        LoggerUtil.logInfo("Kandidat borttagen: " + candidate.getFullName());
    }
}
