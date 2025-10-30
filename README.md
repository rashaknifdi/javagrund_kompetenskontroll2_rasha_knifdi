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

## 🧩 Motivering av klassval utifrån SOLID-principerna

Jag har strukturerat min kod så att den följer flera av SOLID-principerna, särskilt SRP, OCP och DIP. Här är en motivering av mina klassval:

- **Candidate**: Har ett tydligt ansvar att representera en kandidat. Följer SRP.
- **CandidateRepository (interface)**: Abstraktion för datalagring. Följer DIP.
- **CandidateListRepository**: Implementerar datalagring. Följer SRP och DIP.
- **CandidateService**: Hanterar affärslogik som tillägg, borttagning, filtrering och sortering. Följer SRP och DIP.
- **CandidateFilter (interface)**: Definierar strategi för filtrering/sortering. Följer OCP och DIP.
- **BranchFilter, ExperienceFilter, NameSorter**: Varje klass har ett tydligt ansvar. Nya filter kan läggas till utan att ändra befintlig kod. Följer SRP och OCP.
- **LoggerUtil**: Ansvarar för loggning. Följer SRP.
- **InputValidator**: Ansvarar för inmatningskontroll. Följer SRP.
- **ConsoleMenu**: Hanterar användargränssnittet. Följer SRP.

Genom att dela upp ansvar på det här sättet har jag gjort koden lättare att testa, underhålla och bygga vidare på.

---

## 🧪 Enhetstester med mock-objekt

Jag har skapat flera enhetstester med JUnit och Mockito:

- `CandidateServiceTest`: Testar `addCandidate`, `removeCandidate`, `getAllCandidates` med mockat repository.
- `BranchFilterTest`, `ExperienceFilterTest`, `NameSorterTest`: Testar filtrering och sortering.
- `CandidateListRepositoryTest`: Testar lagring och borttagning.

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
   git clone https://github.com/rasha-knifdi/javagrund_kompetenskontroll2_rasha_knifdi.git
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
