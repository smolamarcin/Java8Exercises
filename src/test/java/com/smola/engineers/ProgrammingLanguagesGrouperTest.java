package com.smola.engineers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static com.smola.engineers.ProgrammersProvider.firstSampleProgrammer;
import static com.smola.engineers.ProgrammersProvider.secondSampleProgrammer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ProgrammingLanguagesGrouperTest {
    private Grouper<Collection<ProgrammingLanguage>, Programmer> grouper;

    @BeforeMethod
    public void setUp() {
        grouper = new ProgrammingLanguagesGrouper();
    }

    @Test
    public void shouldRetrieveProgrammingLanguagesFromCollectionOfProgrammers() {
        Collection<ProgrammingLanguage> languages = grouper.group(Arrays.asList(firstSampleProgrammer,secondSampleProgrammer));
        assertThat(languages).extracting("name")
                .containsOnly("Groovy","Java");
    }

}