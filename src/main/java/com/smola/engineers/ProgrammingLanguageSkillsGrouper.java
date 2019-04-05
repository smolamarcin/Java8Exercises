package com.smola.engineers;

import java.util.*;


public class ProgrammingLanguageSkillsGrouper implements Grouper<Map<ProgrammingLanguage, List<Programmer>>, Programmer> {

    @Override
    public Map<ProgrammingLanguage, List<Programmer>> group(Collection<Programmer> toGroup) {
        Map<ProgrammingLanguage, List<Programmer>> languageListMap = groupByLanguages(toGroup);
        return languageListMap;
    }

    private Map<ProgrammingLanguage, List<Programmer>> groupByLanguages(Collection<? extends Programmer> programmers) {
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


}
