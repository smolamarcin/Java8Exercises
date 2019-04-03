package com.smola.engineers;

import java.util.Collection;
import java.util.List;

import static com.smola.engineers.ProgrammersFileConstants.LANGUAGE_NAME_COLUMN;
import static java.util.stream.Collectors.toList;

public class ProgrammingLanguagesGrouper implements Grouper<Collection<ProgrammingLanguage>> {
    @Override
    public Collection<ProgrammingLanguage> group(List<String[]> lines) {
        return lines
                .stream()
                .map(e -> new ProgrammingLanguage(e[LANGUAGE_NAME_COLUMN]))
                .collect(toList());
    }
}
