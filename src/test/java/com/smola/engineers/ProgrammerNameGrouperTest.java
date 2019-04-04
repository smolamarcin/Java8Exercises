package com.smola.engineers;

import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.smola.engineers.ProgrammersProvider.firstSampleProgrammer;
import static com.smola.engineers.ProgrammersProvider.secondSampleProgrammer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


public class ProgrammerNameGrouperTest {
    private Grouper<Map<String, Collection<ProgrammingLanguage>>, Programmer> grouper;
    private CsvFileParser csvFileParser;

    @BeforeMethod
    public void setUp() {
        csvFileParser = mock(CsvFileParser.class);
        grouper = new ProgrammerNameSorter();
    }

    @AfterMethod
    public void tearDown() {
        csvFileParser = null;
    }

    @Test
    public void shouldReturnMap_withProgrammerName_andKnownLanguages() {
        when(csvFileParser.parseFile(anyString())).thenReturn(Arrays.asList(firstSampleProgrammer, secondSampleProgrammer));
        String sampleFileName = "sampleFileName.txt";

        Map<String, Collection<ProgrammingLanguage>> actual = grouper.group(engineersFileReader.parseFile(sampleFileName));

        assertThat(actual.get(firstSampleProgrammer.getName()))
                .hasSize(firstSampleProgrammer.getLanguages().size())
                .containsOnlyElementsOf(firstSampleProgrammer.getLanguages());
    }
}