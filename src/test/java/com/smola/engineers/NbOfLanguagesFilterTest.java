package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.smola.engineers.ProgrammersProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NbOfLanguagesFilterTest {
    private NbOfLanguagesFilter nbOfLanguagesFilter;

    @BeforeClass
    public void setUp() {
        nbOfLanguagesFilter = new NbOfLanguagesFilter();
    }

    @Test
    public void shouldFindProgrammerWithSufficientNumberOfKnownLanguages() {
        int demandedNbOfKnownLanguages = mostSkilledProgrammer.getLanguages().size();
        Map<Programmer, Collection<ProgrammingLanguage>> filtered = nbOfLanguagesFilter.filter(Arrays.asList(firstSampleProgrammer,
                secondSampleProgrammer, mostSkilledProgrammer), demandedNbOfKnownLanguages);

        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.keySet()).containsExactly(mostSkilledProgrammer);
    }
}