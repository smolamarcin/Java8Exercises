package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

import static com.smola.engineers.ProgrammersProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NbOfLanguagesAndNamesGrouperTest {
    private NbOfLanguagesAndNamesSorter sortedGrouper;
    private List<Programmer> toSort;


    @BeforeClass
    public void setUp() {
        toSort = new ArrayList<>();
        toSort.add(firstInAlphabet);
        toSort.add(mostSkilledProgrammer);
        toSort.add(lastAfterSort);
        sortedGrouper = new NbOfLanguagesAndNamesSorter();
    }


    @Test
    public void shouldSortProgrammersByNbOfKnownLanguages_andThenByName() {
        SortedMap<Programmer, Collection<ProgrammingLanguage>> actualSorted = sortedGrouper.group(toSort);

        assertThat(actualSorted).hasSize(3);
        assertThat(actualSorted.firstKey().getName()).isEqualTo(mostSkilledProgrammer.getName());
        assertThat(actualSorted.lastKey().getName()).isEqualTo(lastAfterSort.getName());
    }
}