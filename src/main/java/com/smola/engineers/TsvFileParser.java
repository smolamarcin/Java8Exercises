package com.smola.engineers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

class TsvFileParser {
    private static final String FILE_DELIMITER = "\t";
    private static final int LANGUAGE_NAME_COLUMN = 0;
    private static final int FIRST_PROGRAMMER_COLUMN = LANGUAGE_NAME_COLUMN + 1;

    static Collection<Programmer> parse(String fileName) {
        List<Programmer> allProgrammers = new ArrayList<>();
        for (String[] line : loadFile(fileName)) {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(line[LANGUAGE_NAME_COLUMN]);
            int lastColumn = line.length;
            for (int i = FIRST_PROGRAMMER_COLUMN; i < lastColumn; i++) {
                Programmer programmer = new Programmer(line[i]);
                updateProgrammerLanguages(allProgrammers, programmer, programmingLanguage);
            }
        }
        return allProgrammers;
    }

    private static List<String[]> loadFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(e -> e.split(FILE_DELIMITER))
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    private static void updateProgrammerLanguages(List<Programmer> allProgrammers, Programmer programmer, ProgrammingLanguage programmingLanguage) {
        allProgrammers.stream()
                .filter(e -> e.equals(programmer))
                .findFirst()
                .map(e -> e.addLanguage(programmingLanguage))
                .orElseGet(() -> addNewProgrammer(allProgrammers, programmer, programmingLanguage));
    }

    private static boolean addNewProgrammer(List<Programmer> allProgrammers, Programmer programmer, ProgrammingLanguage programmingLanguage) {
        programmer.addLanguage(programmingLanguage);
        return allProgrammers.add(programmer);
    }
}
