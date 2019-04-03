package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammingLanguageSkillsGrouperTest {
    private Grouper<Map<ProgrammingLanguage, List<Programmer>>> grouper = new ProgrammingLanguageSkillsGrouper();
    private EngineersFileReader engineersFileReader;
    @BeforeClass
    public void setUp() {
        engineersFileReader = new EngineersFileReader(TEST_FILE_NAME);
    }

    @Test
    public void shouldReturnMapWithAvailableProgrammersForEveryLanguage() {
        Map<ProgrammingLanguage, List<Programmer>> availableProgrammers = grouper.group(engineersFileReader.readFile());

        List<Programmer> groovy = availableProgrammers.get(new ProgrammingLanguage("Groovy"));

        assertThat(groovy).containsAnyOf(new Programmer("Z"),
                new Programmer("Y"), new Programmer("X"),
                new Programmer("D"));


    }

}