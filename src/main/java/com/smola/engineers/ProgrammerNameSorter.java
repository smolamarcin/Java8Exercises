package com.smola.engineers;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

class ProgrammerNameSorter implements Grouper<Map<String, Collection<ProgrammingLanguage>>, Programmer> {
    @Override
    public Map<String, Collection<ProgrammingLanguage>> group(Collection<Programmer> collection) {
        return collection.stream()
                .collect(Collectors.toMap(Programmer::getName, Programmer::getLanguages));
    }


}
