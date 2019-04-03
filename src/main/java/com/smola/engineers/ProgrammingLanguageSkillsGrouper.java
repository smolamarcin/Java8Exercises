package com.smola.engineers;

import java.util.*;

import static com.smola.engineers.ProgrammersFileConstants.FIRST_PROGRAMMER_COLUMN;
import static com.smola.engineers.ProgrammersFileConstants.LANGUAGE_NAME_COLUMN;

public class ProgrammingLanguageSkillsGrouper extends AbstractGrouper implements Grouper<Map<ProgrammingLanguage, List<Programmer>>> {


    public ProgrammingLanguageSkillsGrouper(EngineersFileReader engineersFileReader) {
        super(engineersFileReader);
    }

    public Map<ProgrammingLanguage, List<Programmer>> group() {
        List<Programmer> allProgrammers = new ArrayList<>();
        for (String[] line : super.engineersFileReader.readFile()) {
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
