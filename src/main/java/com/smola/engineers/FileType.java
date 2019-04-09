package com.smola.engineers;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

enum FileType {
    CSV(s -> new CsvFileParser().parse(s)),
    TSV(s -> new TsvFileParser().parse(s)),
    UNKNOWN(s -> new UnknownTypeParser().parse(s));
    final Function<String, Collection<Programmer>> parsingStrategy;

    FileType(Function<String, Collection<Programmer>> parsingStrategy) {
        this.parsingStrategy = parsingStrategy;
    }

    static FileType of(String extension) {
        return Arrays.stream(values())
                .filter(e -> e.name().equals(extension))
                .findAny()
                .map(e -> FileType.valueOf(extension))
                .orElse(UNKNOWN);
    }
}
