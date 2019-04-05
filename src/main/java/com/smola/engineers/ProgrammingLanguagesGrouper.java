package com.smola.engineers;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;

public class ProgrammingLanguagesGrouper implements Grouper<Collection<ProgrammingLanguage>, Programmer> {

    @Override
    public Collection<ProgrammingLanguage> group(Collection<Programmer> toGroup) {
        return toGroup
                .stream()
                .flatMap(e -> e.getLanguages().stream())
                .collect(toSet());
    }

}
