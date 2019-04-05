package com.smola.engineers;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.smola.engineers.TestsConstants.CSV_BROKEN_FILE_NAME;
import static com.smola.engineers.TestsConstants.TEST_CSV_FILE_NAME;
import static org.assertj.core.api.Assertions.*;

public class CsvFileParserTest {

    @Test
    public void shouldRetrieveAllProgrammersFromCsvFile() {
        Collection<Programmer> parsed = CsvFileParser.parse(TEST_CSV_FILE_NAME);

        int expectedNbOfProgrammers = 10;
        assertThat(parsed).hasSize(expectedNbOfProgrammers);
    }

    @Test
    public void shouldRetrieveAllLanguagesFromCsvFile() {
        Collection<Programmer> parsed = CsvFileParser.parse(TEST_CSV_FILE_NAME);

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
    public void shouldThrowException_whenFileIsBroken() throws IOException {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CsvFileParser.parse(CSV_BROKEN_FILE_NAME)).withMessageContaining("line 2");
    }

    @Test
    public void dontThrowException_whenFileIsCorrect() {
        assertThatCode(() -> CsvFileParser.parse(TEST_CSV_FILE_NAME))
                .doesNotThrowAnyException();
    }
}