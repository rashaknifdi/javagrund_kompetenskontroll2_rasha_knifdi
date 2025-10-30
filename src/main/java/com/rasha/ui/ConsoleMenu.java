package com.rasha.ui;

import com.rasha.model.Candidate;
import com.rasha.repository.CandidateListRepository;
import com.rasha.service.*;
import com.rasha.service.filter.ExperienceFilter;
import com.rasha.service.filter.BranchFilter;
import com.rasha.service.filter.NameSorter;
import com.rasha.util.InputValidator;
import java.util.List;
import java.util.Scanner;

/**
 * Hanterar användarens interaktion via konsolmeny.
 * Följer SRP genom att endast sköta gränssnittet, inte logik eller lagring.
 */


public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final CandidateService service;

    public ConsoleMenu(CandidateService service) {
        this.service = service;
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addCandidate();
                case "2" -> removeCandidate();
                case "3" -> showAll();
                case "4" -> filterByBranch();
                case "5" -> filterByExperience();
                case "6" -> sortByName();
                case "0" -> {
                    System.out.println("Avslutar programmet...");
                    running = false;
                }
                default -> System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n📋 MENY");
        System.out.println("1. Lägg till kandidat");
        System.out.println("2. Ta bort kandidat");
        System.out.println("3. Visa alla kandidater");
        System.out.println("4. Filtrera efter bransch");
        System.out.println("5. Filtrera efter erfarenhet");
        System.out.println("6. Sortera efter förnamn");
        System.out.println("0. Avsluta");
        System.out.print("Välj ett alternativ: ");
    }

    private void addCandidate() {
        System.out.print("Förnamn: ");
        String firstName = scanner.nextLine();
        if (!InputValidator.isValidName(firstName)) return;

        System.out.print("Efternamn: ");
        String lastName = scanner.nextLine();
        if (!InputValidator.isValidName(lastName)) return;

        System.out.print("Ange ålder: ");
        int age = InputValidator.readInt(scanner);
        if (!InputValidator.isValidAge(age)) return;

        System.out.print("Ange bransch: ");
        String branch = scanner.nextLine();
        if (!InputValidator.isValidBranch(branch)) return;

        System.out.print("Ange antal år erfarenhet: ");
        int experience = InputValidator.readInt(scanner);
        if (!InputValidator.isValidExperience(experience)) return;

        Candidate candidate = new Candidate(firstName, lastName, age, branch, experience);
        service.addCandidate(candidate);
    }

    private void removeCandidate() {
        System.out.print("Förnamn: ");
        String firstName = scanner.nextLine();
        System.out.print("Efternamn: ");
        String lastName = scanner.nextLine();

        Candidate candidate = new Candidate(firstName, lastName, 0, "", 0);
        service.removeCandidate(candidate);
    }

    private void showAll() {
        List<Candidate> candidates = service.getAllCandidates();
        System.out.println("\n Alla kandidater i systemet:");
        candidates.forEach(System.out::println);
    }

    private void filterByBranch() {
        System.out.print("Ange bransch: ");

        String branch = scanner.nextLine();
        if (!InputValidator.isValidBranch(branch)) return;

        List<Candidate> filtered = service.filter(new BranchFilter(branch));
        System.out.println("\n Kandidater med bransch '" + branch + "':");
        filtered.forEach(System.out::println);
    }

    private void filterByExperience() {
        System.out.print("Ange minsta antal år: ");

        int years = InputValidator.readInt(scanner);
        if (!InputValidator.isValidExperience(years)) return;

        List<Candidate> filtered = service.filter(new ExperienceFilter(years));
        System.out.println("\n Kandidater med minst " + years + " år erfarenhet:");
        filtered.forEach(System.out::println);
    }

    private void sortByName() {
        List<Candidate> sorted = service.sort(new NameSorter());
        System.out.println("\n kandidater sorterade efter namn alfabetiskt ordning:");
        sorted.forEach(System.out::println);
    }
}
