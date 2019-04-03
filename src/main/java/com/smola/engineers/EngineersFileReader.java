package com.smola.engineers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

class EngineersFileReader {
    private static final int LANGUAGE_NAME_COLUMN = 0;
    private static final int FIRST_PROGRAMMER_COLUMN = LANGUAGE_NAME_COLUMN + 1;
    private final String fileName;
    private final List<String[]> lines;

    public EngineersFileReader(String fileName) {
        this.fileName = fileName;
        this.lines = new ArrayList<>();
        loadFile();
    }

    private void loadFile() {
        try {
            Files.lines(Paths.get(fileName))
                    .map(e -> e.split("\t"))
                    .forEach(lines::add);
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public Collection<ProgrammingLanguage> readLanguages() {
        return lines
                .stream()
                .map(e -> new ProgrammingLanguage(e[LANGUAGE_NAME_COLUMN]))
                .collect(toList());

    }

    public Map<ProgrammingLanguage, List<Programmer>> findProgramersGroupedByLanguages() {
        List<Programmer> allProgrammers = new ArrayList<>();
        for (String[] line : lines) {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(line[LANGUAGE_NAME_COLUMN]);
            int lastColumn = line.length;
            for (int i = FIRST_PROGRAMMER_COLUMN; i < lastColumn; i++) {
                Programmer programmer = new Programmer(line[i]);
                updateProgrammerLanguages(allProgrammers, programmer, programmingLanguage);
            }
        }

        Map<ProgrammingLanguage, List<Programmer>> languageListMap = groupByLanguages(allProgrammers);
        return languageListMap;
    }

    private Map<ProgrammingLanguage, List<Programmer>> groupByLanguages(List<Programmer> programmers) {
        Map<ProgrammingLanguage, List<Programmer>> languageListMap = new HashMap<>();

        for (Programmer programmer : programmers) {
            for (ProgrammingLanguage programmingLanguage : programmer.getLanguages()) {
                languageListMap.merge(programmingLanguage, new ArrayList<>(Collections.singletonList(programmer)), (l1, l2) -> {
                    l2.addAll(l1);
                    return l2;
                });
            }
        }
        return languageListMap;
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
