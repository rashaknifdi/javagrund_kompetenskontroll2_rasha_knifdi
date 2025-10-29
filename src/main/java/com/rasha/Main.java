package com.rasha;

import com.rasha.repository.CandidateListRepository;
import com.rasha.service.CandidateService;
import com.rasha.ui.ConsoleMenu;

// Startar applikationen och kopplar ihop repository, service och menu.


public class Main {
    public static void main(String[] args) {
        CandidateListRepository repository = new CandidateListRepository();
        CandidateService service = new CandidateService(repository);
        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
    }
}
