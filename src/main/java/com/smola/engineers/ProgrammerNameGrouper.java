package com.smola.engineers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class ProgrammerNameGrouper implements Grouper<Map<String, Set<ProgrammingLanguage>>, Programmer> {
    @Override
    public Map<String, Set<ProgrammingLanguage>> group(Collection<? extends Programmer> collection) {
        return collection.stream()
                .collect(Collectors.toMap(Programmer::getName, Programmer::getLanguages));
    }


}
