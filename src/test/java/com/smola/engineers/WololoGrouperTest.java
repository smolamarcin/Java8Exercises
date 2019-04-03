package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;


public class WololoGrouperTest {
    private Grouper<Map<String, Set<ProgrammingLanguage>>> grouper;
    private EngineersFileReader engineersFileReader;

    @BeforeClass
    public void setUp() {
        engineersFileReader = new EngineersFileReader(TEST_FILE_NAME);
        grouper = new WololoGrouper();
    }

    @Test
    public void shouldReturnMap_withProgrammerName_andKnownLanguages() {
        Map<String, Set<ProgrammingLanguage>> actual = grouper.group(engineersFileReader.readFile());

        assertThat(actual.get("Z")).containsExactly(new ProgrammingLanguage("Java"),new ProgrammingLanguage("Groovy"));
    }
}