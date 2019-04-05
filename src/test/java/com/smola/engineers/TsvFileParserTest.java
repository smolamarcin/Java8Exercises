package com.smola.engineers;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.smola.engineers.TestsConstants.TEST_TSV_BROKEN_FILE;
import static com.smola.engineers.TestsConstants.TEST_TSV_FILE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TsvFileParserTest {
    @Test
    public void shouldRetrieveAllProgrammersFromCsvFile() {
        Collection<Programmer> parsed = TsvFileParser.parse(TEST_TSV_FILE_NAME);

        int expectedNbOfProgrammers = 10;
        assertThat(parsed).hasSize(expectedNbOfProgrammers);
    }

    @Test
    public void shouldRetrieveAllLanguagesFromCsvFile() {
        Collection<Programmer> parsed = TsvFileParser.parse(TEST_TSV_FILE_NAME);

        int expectedNbOfLanguages = 5;
        assertThat(getParsedLanguages(parsed)).hasSize(expectedNbOfLanguages);
    }

    private Set<ProgrammingLanguage> getParsedLanguages(Collection<Programmer> parsed) {
        return parsed.stream()
                .map(Programmer::getLanguages)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Test
    public void shouldThrowException_whenFileIsBroken() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TsvFileParser.parse(TEST_TSV_BROKEN_FILE))
                .withMessageContaining("line 3");
    }
}