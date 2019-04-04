package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.asList;

public class NbOfLanguagesFilterTest {
    private NbOfLanguagesFilter nbOfLanguagesFilter;

    @BeforeClass
    public void setUp() {
        nbOfLanguagesFilter = new NbOfLanguagesFilter();
    }

    @Test
    public void shouldFindProgrammerWithSufficientNumberOfKnownLanguages() {
        Programmer firstDev = new Programmer("First", Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Groovy")));
        Programmer secondDev = new Programmer("Second", Arrays.asList(new ProgrammingLanguage("Java")));

        Map<Programmer, Collection<ProgrammingLanguage>> filtered = nbOfLanguagesFilter.filter(Arrays.asList(firstDev, secondDev), 2);

        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.keySet()).containsExactly(new Programmer("First"));
    }
}