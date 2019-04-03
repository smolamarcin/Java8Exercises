package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.smola.engineers.TestsConstants.TEST_FILE_NAME;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class EngineersFileReaderTest {
    private EngineersFileReader engineersFileReader;


    @BeforeClass
    public void setUp() {
        engineersFileReader = new EngineersFileReader(TEST_FILE_NAME);
    }


    @Test
    public void shouldReadALlFileLines() {
        List<String[]> lines = engineersFileReader.readFile();

        assertThat(lines.size()).isEqualTo(5);
    }


}