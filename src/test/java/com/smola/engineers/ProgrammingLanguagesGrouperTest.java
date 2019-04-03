package com.smola.engineers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammingLanguagesGrouperTest {
    private Grouper<Collection<ProgrammingLanguage>> grouper;
    private EngineersFileReader engineersFileReader;

    @BeforeMethod
    public void setUp() {
        engineersFileReader = new EngineersFileReader(TEST_FILE_NAME);
        grouper = new ProgrammingLanguagesGrouper(engineersFileReader);
    }

    @Test
    public void shouldReadAllLanguagesFromFile() {
        Collection<ProgrammingLanguage> languages = grouper.group();
        assertThat(languages).extracting("name")
                .containsOnly("Groovy", "Java", "C++", "C#", "Scala");
    }

}