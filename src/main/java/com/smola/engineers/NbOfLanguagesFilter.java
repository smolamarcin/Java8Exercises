package com.smola.engineers;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toMap;

class NbOfLanguagesFilter implements Filter<Programmer, Collection<ProgrammingLanguage>, Programmer> {

    public Map<Programmer, Collection<ProgrammingLanguage>> filter(Collection<Programmer> programmers, int demandedLanguagesNumber) {
        return filter(programmers, nbOfKnownLanguagesPredicate(demandedLanguagesNumber));

    }

    private Predicate<Programmer> nbOfKnownLanguagesPredicate(int demandedLanguagesNumber) {
        return e -> e.getLanguages().size() >= demandedLanguagesNumber;
    }

    @Override
    public Map<Programmer, Collection<ProgrammingLanguage>> filter(Collection<Programmer> toFilter, Predicate<? super Programmer> predicate) {
        return toFilter.stream()
                .filter(predicate)
                .collect(toMap(k -> k, Programmer::getLanguages));
    }
}
