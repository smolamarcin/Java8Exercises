package com.smola.engineers;

import java.util.Collection;
import java.util.List;

import static com.smola.engineers.ProgrammersFileConstants.LANGUAGE_NAME_COLUMN;
import static java.util.stream.Collectors.toList;

public class ProgrammingLanguagesGrouper extends AbstractGrouper implements Grouper<Collection<ProgrammingLanguage>> {
    ProgrammingLanguagesGrouper(EngineersFileReader engineersFileReader) {
        super(engineersFileReader);
    }

    @Override
    public Collection<ProgrammingLanguage> group() {
        return engineersFileReader
                .readFile()
                .stream()
                .map(e -> new ProgrammingLanguage(e[LANGUAGE_NAME_COLUMN]))
                .collect(toList());
    }
}
