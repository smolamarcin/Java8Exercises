package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class EngineersFileReaderTest {
    private EngineersFileReader engineersFileReader;
    private static final String TEST_FILE_NAME = "src/test/resources/Programmers-test.tsv";

    @BeforeClass
    public void setUp() {
        engineersFileReader = new EngineersFileReader(TEST_FILE_NAME);
    }

    @Test
    public void shouldLoadAllLines() throws IOException {
        Stream<String> stringStream = engineersFileReader.loadFileIntoStream(TEST_FILE_NAME);
        assertThat(stringStream.collect(toList()).size()).isEqualTo(5);
    }

    @Test
    public void shouldReadAllLanguagesFromFile() {
        Collection<ProgrammingLanguage> languages = engineersFileReader.readLanguages();
        assertThat(languages).extracting("name")
                .containsOnly("Groovy", "Java", "C++", "C#", "Scala");
    }

    @Test
    public void shouldReturnMapWithAvailableProgrammersForEveryLanguage() {
        Map<ProgrammingLanguage, List<Programmer>> availableProgrammers = engineersFileReader.findProgramersGroupedByLanguages();

        List<Programmer> groovy = availableProgrammers.get(new ProgrammingLanguage("Groovy"));

        assertThat(groovy).containsAnyOf(new Programmer("Z"),
                new Programmer("Y"), new Programmer("X"),
                new Programmer("D"));


    }

}