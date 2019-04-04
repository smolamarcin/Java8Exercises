package com.smola.engineers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.smola.engineers.ProgrammersProvider.firstSampleProgrammer;
import static com.smola.engineers.ProgrammersProvider.secondSampleProgrammer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ProgrammingLanguageSkillsGrouperTest {
    private Grouper<Map<ProgrammingLanguage, List<Programmer>>, Programmer> grouper;

    @BeforeClass
    public void setUp() {
        grouper = new ProgrammingLanguageSkillsGrouper();
    }

    @Test
    public void shouldReturnMapWithAvailableProgrammersForEveryLanguage() {
        Collection<Programmer> programmersToGroup = Arrays.asList(firstSampleProgrammer,secondSampleProgrammer);
        Map<ProgrammingLanguage, List<Programmer>> availableProgrammers = grouper.group(programmersToGroup);

        List<Programmer> groovy = availableProgrammers.get(new ProgrammingLanguage("Groovy"));

        assertThat(groovy).containsAnyOf(firstSampleProgrammer);


    }

}