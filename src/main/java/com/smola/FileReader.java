package com.smola;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public interface FileReader {
    default Stream<String> loadFileIntoStream(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }
}
