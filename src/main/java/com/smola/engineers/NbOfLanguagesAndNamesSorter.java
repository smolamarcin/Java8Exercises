package com.smola.engineers;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

class NbOfLanguagesAndNamesSorter implements Sorter<SortedMap<Programmer, Collection<ProgrammingLanguage>>, Programmer>{

    @Override
    public SortedMap<Programmer, Collection<ProgrammingLanguage>> sort(Collection<? extends Programmer> programmers) {
        Comparator<Programmer> nbOfLanguages_Name_Comparator = (o1, o2) -> {
            if (o1.getLanguages().size() == o2.getLanguages().size()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return Integer.compare(o2.getLanguages().size(), o1.getLanguages().size());

            }
        };
        Map<Programmer, Collection<ProgrammingLanguage>> collect = programmers.stream()
                .collect(toMap(k -> k, Programmer::getLanguages));
        SortedMap<Programmer, Collection<ProgrammingLanguage>> sorted = new TreeMap<>(nbOfLanguages_Name_Comparator);
        sorted.putAll(collect);
        return sorted;
    }
}
