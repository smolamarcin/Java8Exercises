package com.smola.engineers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.IObjectFactory;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.smola.engineers.ProgrammersProvider.firstSampleProgrammer;
import static com.smola.engineers.ProgrammersProvider.secondSampleProgrammer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(CsvFileParser.class)
public class ProgrammerNameGrouperTest extends PowerMockTestCase {
    private Grouper<Map<String, Collection<ProgrammingLanguage>>, Programmer> grouper;


    @BeforeMethod
    public void setUp() {
        grouper = new ProgrammerNameSorter();
    }


    @Test
    public void shouldReturnMap_withProgrammerName_andKnownLanguages() {
        PowerMockito.mockStatic(CsvFileParser.class);
        String sampleFileName = "sampleFileName.txt";
        when(CsvFileParser.parse(sampleFileName)).thenReturn(Arrays.asList(firstSampleProgrammer, secondSampleProgrammer));

        Map<String, Collection<ProgrammingLanguage>> actual = grouper.group(CsvFileParser.parse(sampleFileName));

        assertThat(actual.get(firstSampleProgrammer.getName()))
                .hasSize(firstSampleProgrammer.getLanguages().size())
                .containsOnlyElementsOf(firstSampleProgrammer.getLanguages());
    }
}