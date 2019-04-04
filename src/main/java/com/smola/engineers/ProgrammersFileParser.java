package com.smola.engineers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

class ProgrammersFileParser extends AbstractFileParser<Programmer> {
    private static final String FILE_DELIMITER = "\t";
    private static final int LANGUAGE_NAME_COLUMN = 0;
    private static final int FIRST_PROGRAMMER_COLUMN = LANGUAGE_NAME_COLUMN + 1;

    ProgrammersFileParser(String fileName) {
        super(fileName);
    }

    private List<String[]> loadFile() {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(e -> e.split(FILE_DELIMITER))
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }


    @Override
    public Collection<Programmer> parseFile() {
        List<Programmer> allProgrammers = new ArrayList<>();
        for (String[] line : this.loadFile()) {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(line[LANGUAGE_NAME_COLUMN]);
            int lastColumn = line.length;
            for (int i = FIRST_PROGRAMMER_COLUMN; i < lastColumn; i++) {
                Programmer programmer = new Programmer(line[i]);
                updateProgrammerLanguages(allProgrammers, programmer, programmingLanguage);
            }
        }
        return allProgrammers;
    }

    private void updateProgrammerLanguages(List<Programmer> allProgrammers, Programmer programmer, ProgrammingLanguage programmingLanguage) {
        allProgrammers.stream()
                .filter(e -> e.equals(programmer))
                .findFirst()
                .map(e -> e.addLanguage(programmingLanguage))
                .orElseGet(() -> addNewProgrammer(allProgrammers, programmer, programmingLanguage));
    }

    private boolean addNewProgrammer(List<Programmer> allProgrammers, Programmer programmer, ProgrammingLanguage programmingLanguage) {
        programmer.addLanguage(programmingLanguage);
        return allProgrammers.add(programmer);
    }
}
