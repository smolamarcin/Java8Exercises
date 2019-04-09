package com.smola.engineers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.smola.engineers.ProgrammersProvider.firstSampleProgrammer;
import static com.smola.engineers.ProgrammersProvider.secondSampleProgrammer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammerNameGrouperTest {
    private Grouper<Map<String, Collection<ProgrammingLanguage>>, Programmer> grouper;


    @BeforeMethod
    public void setUp() {
        grouper = new ProgrammerNameSorter();
    }

    @Test
    public void shouldReturnMap_withProgrammerName_andKnownLanguages() {
        CsvFileParser csvFileParser = mock(CsvFileParser.class);
        String sampleFileName = "sampleFileName.txt";
        when(csvFileParser.parse(sampleFileName)).thenReturn(Arrays.asList(firstSampleProgrammer, secondSampleProgrammer));

        Map<String, Collection<ProgrammingLanguage>> actual = grouper.group(csvFileParser.parse(sampleFileName));

        assertThat(actual.get(firstSampleProgrammer.getName()))
                .hasSize(firstSampleProgrammer.getLanguages().size())
                .containsOnlyElementsOf(firstSampleProgrammer.getLanguages());
    }
}