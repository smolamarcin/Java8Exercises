package com.smola.engineers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ProgrammersProvider {
    static final Set<ProgrammingLanguage> firstProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Scala")));
    static final Set<ProgrammingLanguage> secondProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("C++")
            , new ProgrammingLanguage("Java"), new ProgrammingLanguage("Scala"), new ProgrammingLanguage("Kotlin")));
    static final Set<ProgrammingLanguage> thirdProgrammingLanguages = new HashSet<>(Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Kotlin")));
    static final Programmer mostSkilledProgrammer = new Programmer("Zb", secondProgrammingLanguages);
    ;
    static final Programmer firstInAlphabet = new Programmer("A", firstProgrammingLanguages);
    ;
    static final Programmer lastAfterSort = new Programmer("Za", thirdProgrammingLanguages);
    ;
    static final Programmer firstSampleProgrammer = new Programmer("First", Arrays.asList(new ProgrammingLanguage("Java"), new ProgrammingLanguage("Groovy")));
    static final Programmer secondSampleProgrammer = new Programmer("Second", Arrays.asList(new ProgrammingLanguage("Java")));

}
