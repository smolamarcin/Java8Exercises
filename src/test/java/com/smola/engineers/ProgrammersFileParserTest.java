package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammersFileParserTest {
    private ProgrammersFileParser engineersFileReader;


    @BeforeClass
    public void setUp() {
        engineersFileReader = new ProgrammersFileParser(TEST_FILE_NAME);
    }


    @Test
    public void shouldReadAllProgrammersFromFile() {
        // given
        Collection<Programmer> programmers = engineersFileReader.parseFile();

        // when -then
        assertThat(programmers.size()).isEqualTo(10);


    }

    @Test
    public void shouldReadAllLanguagesFromFile() {
        // given
        Collection<Programmer> programmers = engineersFileReader.parseFile();
        Collection<ProgrammingLanguage> uniqueLanguages = getUniqueLanguages(programmers);

        // when - then
        assertThat(uniqueLanguages.size()).isEqualTo(5);
        assertThat(uniqueLanguages).containsExactlyInAnyOrder(new ProgrammingLanguage("Java"),
                new ProgrammingLanguage("Groovy"),
                new ProgrammingLanguage("C#"),
                new ProgrammingLanguage("C++"),
                new ProgrammingLanguage("Scala"));
    }

    private Collection<ProgrammingLanguage> getUniqueLanguages(Collection<Programmer> programmers) {
        return programmers.stream()
                .flatMap(e -> e.getLanguages().stream())
                .collect(toSet());
    }


}