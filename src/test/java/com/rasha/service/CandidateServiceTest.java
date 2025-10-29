package com.rasha.service;

import com.rasha.model.Candidate;
import com.rasha.repository.CandidateRepository;
import com.rasha.service.filter.CandidateFilter;
import com.rasha.service.filter.ExperienceFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CandidateServiceTest {

    private CandidateRepository repository = mock(CandidateRepository.class);;
    private CandidateService service  = new CandidateService(repository);;

    @Test
    void shouldAddCandidateIfNotDuplicate() {
        Candidate newCandidate = new Candidate("Rasha", "Knifdi", 36, "IT", 3);

        when(repository.findAll()).thenReturn(List.of());

        service.addCandidate(newCandidate);

        verify(repository).save(newCandidate);

        when(repository.findAll()).thenReturn(List.of(newCandidate));

        List<Candidate> result = service.getAllCandidates();
        assertEquals(1, result.size());
        assertEquals("Rasha", result.get(0).getFirstName());
    }

    @Test
    void shouldNotAddDuplicateCandidate() {
        Candidate existing = new Candidate("Rasha", "Knifdi", 36, "IT", 3);

        when(repository.findAll()).thenReturn(List.of(existing));

        service.addCandidate(existing);

        verify(repository, never()).save(any());
    }

    @Test
    void shouldRemoveCandidate() {
        Candidate candidate =new Candidate("Rasha", "Knifdi", 36, "IT", 3);

        when(repository.findAll()).thenReturn(List.of(candidate));

        service.removeCandidate(candidate);

        verify(repository).delete(candidate);

        when(repository.findAll()).thenReturn(List.of()); // tom lista

        List<Candidate> result = service.getAllCandidates();
        assertEquals(0, result.size()); // Bekräfta att listan är tom
    }

    @Test
    void shouldReturnAllCandidates() {
        Candidate c1 = new Candidate("Rasha", "Knifdi", 36, "IT", 3);
        Candidate c2 = new Candidate("Sayer", "Fadel", 45, "finance", 6);

        when(repository.findAll()).thenReturn(List.of(c1, c2));

        List<Candidate> result = service.getAllCandidates();
        assertEquals(2, result.size());
        assertEquals("Rasha", result.get(0).getFirstName());
        assertEquals("Sayer", result.get(1).getFirstName());
    }

}
