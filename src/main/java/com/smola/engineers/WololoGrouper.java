package com.smola.engineers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.smola.engineers.ProgrammersFileConstants.FIRST_PROGRAMMER_COLUMN;
import static com.smola.engineers.ProgrammersFileConstants.LANGUAGE_NAME_COLUMN;

//todo: name!!! -> this class return a map, where key is programmer name and value is list of his languages
class WololoGrouper extends AbstractGrouper implements Grouper<Map<String, Set<ProgrammingLanguage>>> {
    public WololoGrouper(EngineersFileReader engineersFileReader) {
        super(engineersFileReader);
    }

    @Override
    public Map<String, Set<ProgrammingLanguage>> group() {
        List<Programmer> allProgrammers = new ArrayList<>();
        for (String[] line : super.engineersFileReader.readFile()) {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(line[LANGUAGE_NAME_COLUMN]);
            int lastColumn = line.length;
            for (int i = FIRST_PROGRAMMER_COLUMN; i < lastColumn; i++) {
                Programmer programmer = new Programmer(line[i]);
                updateProgrammerLanguages(allProgrammers, programmer, programmingLanguage);
            }
        }

        return allProgrammers.stream()
                .collect(Collectors.toMap(Programmer::getName, Programmer::getLanguages));
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
