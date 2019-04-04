package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NbOfLanguagesAndNamesSorterTest {
    private NbOfLanguagesAndNamesSorter sortedGrouper;
    private List<Programmer> toSort;
    Set<ProgrammingLanguage> firstProgrammingLanguages;
    Set<ProgrammingLanguage> secondProgrammingLanguages;
    Set<ProgrammingLanguage> thirdProgrammingLanguages;
    Programmer mostSkilledProgrammer;
    Programmer firstInAlphabet;
    Programmer lastAfterSort;


    @BeforeClass
    public void setUp() {
        toSort = new ArrayList<>();
        prepareProgrammingLanguages();
        preprareProgrammers();
        toSort.add(firstInAlphabet);
        toSort.add(mostSkilledProgrammer);
        toSort.add(lastAfterSort);
        sortedGrouper = new NbOfLanguagesAndNamesSorter();
    }

    private void preprareProgrammers() {
        mostSkilledProgrammer = new Programmer("Zb", secondProgrammingLanguages);
        firstInAlphabet = new Programmer("A", firstProgrammingLanguages);
        lastAfterSort= new Programmer("Za", thirdProgrammingLanguages);
    }

    private void prepareProgrammingLanguages() {
        firstProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Scala")));

        secondProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("C++")
                , new ProgrammingLanguage("Java"), new ProgrammingLanguage("Scala"), new ProgrammingLanguage("Kotlin")));

        thirdProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Kotlin")));
    }

    @Test
    public void shouldSortProgrammersByNbOfKnownLanguages_andThenByName() {
        SortedMap<Programmer, Collection<ProgrammingLanguage>> actualSorted = sortedGrouper.sort(toSort);

        assertThat(actualSorted.size()).isEqualTo(3);
        assertThat(actualSorted.firstKey().getName()).isEqualTo("Zb");
        assertThat(actualSorted.lastKey().getName()).isEqualTo("Za");
    }
}