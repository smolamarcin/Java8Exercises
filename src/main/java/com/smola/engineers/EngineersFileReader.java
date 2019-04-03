package com.smola.engineers;

import com.smola.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class EngineersFileReader implements FileReader {
    public static final int LANGUAGE_NAME_COLUMN = 0;
    private final String fileName;
    private final List<String[]> lines;

    public EngineersFileReader(String fileName) {
        this.fileName = fileName;
        lines = new ArrayList<>();
        loadFile();
    }

    private void loadFile() {
        try (Stream<String> linesStream = this.loadFileIntoStream(fileName)) {
            linesStream
                    .map(e -> e.split("\t"))
                    .forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
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
        lines.forEach(line -> {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(line[LANGUAGE_NAME_COLUMN]);
            for (int i = LANGUAGE_NAME_COLUMN + 1; i < line.length; i++) {
                Programmer programmer = new Programmer(line[i]);
                updateProgrammerLanguages(allProgrammers, programmer, programmingLanguage);
            }
        });

        Map<ProgrammingLanguage, List<Programmer>> languageListMap = groupByLanguages(allProgrammers);
        return languageListMap;
    }

    private Map<ProgrammingLanguage, List<Programmer>> groupByLanguages(List<Programmer> programmers) {
        Map<ProgrammingLanguage, List<Programmer>> languageListMap = new HashMap<>();
        for (Programmer programmer : programmers) {
            programmer.getLanguages().forEach(e -> {
                if (languageListMap.containsKey(e)) {
                    List<Programmer> currentLanguageProgrammers = languageListMap.get(e);
                    currentLanguageProgrammers.add(programmer);
                } else {
                    List<Programmer> currentLanguageProgrammers = new ArrayList<>();
                    currentLanguageProgrammers.add(programmer);
                    languageListMap.put(e, currentLanguageProgrammers);
                }
            });
        }
        return languageListMap;
    }

    private void updateProgrammerLanguages(List<Programmer> allProgrammers, Programmer programmer, ProgrammingLanguage programmingLanguage) {
        Optional<Programmer> toUpdate = allProgrammers.stream()
                .filter(e -> e.equals(programmer))
                .findFirst();

        if (toUpdate.isPresent()) {
            toUpdate.get().addLanguage(programmingLanguage);
        } else {
            programmer.addLanguage(programmingLanguage);
            allProgrammers.add(programmer);
        }

    }
}
