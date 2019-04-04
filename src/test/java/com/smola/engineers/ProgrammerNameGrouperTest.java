package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Map;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;


public class ProgrammerNameGrouperTest {
    private Grouper<Map<String, Collection<ProgrammingLanguage>>, Programmer> grouper;
    private ProgrammersFileParser engineersFileReader;

    @BeforeClass
    public void setUp() {
        engineersFileReader = new ProgrammersFileParser(TEST_FILE_NAME);
        grouper = new ProgrammerNameSorter();
    }

    @Test
    public void shouldReturnMap_withProgrammerName_andKnownLanguages() {
        Map<String, Collection<ProgrammingLanguage>> actual = grouper.group(engineersFileReader.parseFile());

        assertThat(actual.get("Z")).containsExactly(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Groovy"));
    }
}