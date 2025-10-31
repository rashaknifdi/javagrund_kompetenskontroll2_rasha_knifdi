# 📋 Rekryteringssystem – README

## 🧠 Projektbeskrivning

Det här är ett konsolbaserat rekryteringssystem som hjälper rekryterare att hantera kandidater. Systemet erbjuder funktioner för att lägga till, ta bort, visa och filtrera kandidater baserat på bransch, erfarenhet och namn. Applikationen är byggd med fokus på tydlig struktur, testbarhet och möjlighet till framtida expansion.

---

## 🗂️ Funktioner

- Lägg till nya kandidater
- Ta bort kandidater
- Visa alla kandidater
- Filtrera kandidater efter bransch
- Filtrera kandidater efter antal år erfarenhet
- Sortera kandidater efter förnamn

---

## 🧱 Datastruktur

Kandidater lagras i en `List<Candidate>` via `CandidateListRepository`, som implementerar ett `CandidateRepository`-interface. 
Jag valde List eftersom det passar bra när man vill arbeta med hela samlingen av kandidater – till exempel för att visa, filtrera eller sortera dem. Det fungerar smidigt med Stream API och lambdauttryck, vilket gör koden både tydlig och effektiv. 
Listan är inkapslad i ett repository för att separera lagring från logik (SRP och DIP). 
Jag övervägde även Set och Map, men List gav bäst flexibilitet och läsbarhet för det här projektet.

---

## 🔄 Stream API 

För filtrering och sortering används **Stream API** och **lambdauttryck** i filterklasser som `BranchFilter`, `ExperienceFilter` och `NameSorter`. Exempel:

- Filtrering: `filter(c -> c.getBranch().equalsIgnoreCase(branch))`
- Sortering: `sorted((c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName()))`

---

## 🧩 SOLID-principer i klassdesign

Jag har strukturerat min kod så att den följer flera av **SOLID-principerna**:  
**SRP (Single Responsibility Principle)**, **OCP (Open/Closed Principle)**,  
**DIP (Dependency Inversion Principle)**, **LSP (Liskov Substitution Principle)** och  
**ISP (Interface Segregation Principle)**. Här är en motivering av mina klassval:

- **`Candidate`** – Representerar en kandidat med tydligt ansvar. Följer **SRP**.
- **`CandidateRepository` (interface)** – Abstraktion för datalagring. Följer **DIP**.
- **`CandidateListRepository`** – Implementerar datalagring. Följer **SRP** och **DIP**.
- **`CandidateService`** – Hanterar affärslogik som tillägg, borttagning, filtrering och sortering. Följer **SRP** och **DIP**.
- **`CandidateFilter` (interface)** – Definierar strategi för filtrering/sortering. Följer **OCP** och **ISP**.  
  Används av `CandidateService` som en abstraktion – vilket bidrar till att **DIP** efterlevs.
- **`BranchFilter`, `ExperienceFilter`, `NameSorter`** – Varje klass har ett tydligt ansvar.  
  Nya filter kan läggas till utan att ändra befintlig kod. Följer **SRP**, **OCP** och **LSP**.
- **`LoggerUtil`** – Ansvarar för loggning. Följer **SRP**.
- **`InputValidator`** – Ansvarar för inmatningskontroll. Följer **SRP**.
- **`ConsoleMenu`** – Hanterar användargränssnittet. Följer **SRP**.

---

## ✍️ Fördjupad motivering av två klassval

För att konkretisera hur jag har tillämpat **SOLID-principerna** i praktiken vill jag lyfta fram två centrala klassval:

### 📌 `CandidateService` – affärslogik med tydlig ansvarsfördelning

`CandidateService` ansvarar för operationer som att lägga till, ta bort, filtrera och sortera kandidater. Den hanterar inte datalagring direkt, utan använder ett `CandidateRepository`-interface.

- **SRP** – Har ett tydligt och avgränsat ansvar: affärslogik.
- **DIP** – Beror på abstraktioner (`CandidateRepository`, `CandidateFilter`) istället för konkreta implementationer, vilket gör klassen lätt att testa och utöka.

### 📌 `CandidateFilter` och dess implementationer – flexibel och utbyggbar filtrering

`CandidateFilter` är ett interface som definierar en strategi för filtrering eller sortering. Varje implementation, som `BranchFilter`, `ExperienceFilter` och `NameSorter`, har ett tydligt och avgränsat ansvar.

- **OCP** – Nya filter kan läggas till utan att ändra befintlig kod.
- **SRP** – Varje filterklass fokuserar på en enda uppgift.
- **LSP** – Alla filterklasser kan användas där `CandidateFilter` förväntas – utan att bryta funktionalitet.
- **ISP** – Interfacet är smalt och fokuserat, med endast en metod (`apply`), vilket gör det enkelt att implementera utan onödiga beroenden.

---

## 🧪 Enhetstester med mock-objekt

Jag har skapat flera enhetstester med JUnit och Mockito:

- `CandidateServiceTest`: Testar `addCandidate`, `removeCandidate`, `getAllCandidates` med mockat repository.
- `BranchFilterTest`, `ExperienceFilterTest`, `NameSorterTest`: Testar filtrering och sortering.
- `CandidateListRepositoryTest`: Testar lagring och borttagning.
- `InputValidatorTest` – testar validering av namn, ålder, bransch och erfarenhetsår

Mock-objekt används för att isolera beroenden och testa logik utan att påverka datalagring.

---

## 📝 Loggning med SLF4J

Loggning sker via `LoggerUtil` med SLF4J. Viktiga händelser som tillägg, borttagning, filtrering och sortering loggas med `info`, `warn` och `error`. Loggningen är inte kopplad till fil, men ger tydlig feedback i konsolen.

---

## 💬 Reflektion: Prompt-engineering

Jag har använt AI som stöd och märkt att det spelar stor roll hur jag ställer frågan. Jag har testat olika prompts och jämfört svaren.

### Exempel 1 – Testning

- Prompt 1: *Hur testar jag min klass?* → Svaret var allmänt.
- Prompt 2: *Hur skriver jag enhetstester med mock och verify enligt DIP?* → Svaret var mycket bättre och hjälpte mig att skriva tester för `CandidateService`.

### Exempel 2 – Dokumentation

- Prompt 1: *Hur skriver jag README?* → Svaret blev mest om rubriker.
- Prompt 2: *Hur kan jag motivera mina klassval i README utifrån SOLID-principerna?* → Svaret hjälpte mig att förklara mina designval tydligt.

### Exempel 3 – OCP

- Prompt 1: *Vad betyder OCP?* → Svaret var en definition.
- Prompt 2: *Hur kan jag följa OCP med interface och arv?* → Svaret visade hur jag kan lägga till nya filterklasser utan att ändra befintlig kod.

Jag har använt AI som en pedagogisk verktyg för att förstå, reflektera och skriva själv. Det har gjort mig mer medveten om hur jag formulerar tekniska problem – både i kod och dokumentation – och hjälpt mig att skriva bättre och mer genomtänkt kod.

---

## 🧪 Så kör du projektet via Git Bash, windows powershell eller terminalen:

1. Klona projektet:
   ```bash
   git clone https://github.com/rashaknifdi/javagrund_kompetenskontroll2_rasha_knifdi.git
   cd javagrund_kompetenskontroll2_rasha_knifdi

2. Kör Main med Maven:
   ```bash
   mvn compile
   mvn exec:java

3. kör tester med Maven
    ```bash
    mvn test

----
## 👤 Utvecklare
- Namn: Rasha Knifdi

- Roll: Fullstack Java Student
