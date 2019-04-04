package com.smola.engineers;

import java.util.*;

class Programmer {
    private final String name;
    private Collection<ProgrammingLanguage> languages;

    Programmer(String name, Collection<ProgrammingLanguage> languages) {
        this.name = name;
        this.languages = languages;
    }

    public Programmer(String name) {
        this.name = name;
        languages = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    boolean addLanguage(ProgrammingLanguage language) {
        return this.languages.add(language);
    }

    public Collection<ProgrammingLanguage> getLanguages() {
        return Collections.unmodifiableCollection(languages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "name='" + name + '\'' +
                '}';
    }
}
