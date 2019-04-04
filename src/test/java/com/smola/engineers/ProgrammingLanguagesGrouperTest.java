package com.smola.engineers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammingLanguagesGrouperTest {
    private Grouper<Collection<ProgrammingLanguage>, Programmer> grouper;
    private ProgrammersFileParser engineersFileReader;

    @BeforeMethod
    public void setUp() {
        engineersFileReader = new ProgrammersFileParser(TEST_FILE_NAME);
        grouper = new ProgrammingLanguagesGrouper();
    }

    @Test
    public void shouldReadAllLanguagesFromFile() {
        Collection<ProgrammingLanguage> languages = grouper.group(engineersFileReader.parseFile());
        assertThat(languages).extracting("name")
                .containsOnly("Groovy", "Java", "C++", "C#", "Scala");
    }

}