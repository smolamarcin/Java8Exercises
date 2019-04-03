package com.smola.engineers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.smola.engineers.ProgrammersFileConstants.FILE_DELIMITER;
import static java.util.stream.Collectors.toList;

class EngineersFileReader implements FileReader{

    private final String fileName;

    public EngineersFileReader(String fileName) {
        this.fileName = fileName;
    }

    public List<String[]> readFile() {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(e -> e.split(FILE_DELIMITER))
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }


}
