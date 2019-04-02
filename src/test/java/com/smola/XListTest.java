package com.smola;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class XListTest {
    @Test
    public void diff() {
        XList<Integer> xList = XList.of(1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5);
        Set<Integer> toDelete = new HashSet<>(Arrays.asList(3, 4, 5));
        XList diff = xList.diff(toDelete);
        assertFalse(diff.contains(3));
        assertFalse(diff.contains(4));
        assertFalse(diff.contains(5));
    }

    @Test
    public void xListWithoutDuplicates() {
        XList<Integer> withDuplicates = XList.of(1, 1, 2, 3, 4, 4, 4, 4);
        XList<Integer> withoutDuplicates = withDuplicates.unique();

        assertThat(withoutDuplicates.size()).isEqualTo(4);
        assertThat(withoutDuplicates.contains(1));
        assertThat(withoutDuplicates.contains(2));
        assertThat(withoutDuplicates.contains(3));
        assertThat(withoutDuplicates.contains(4));
    }

    @Test
    public void shouldJoinElements_withDelimiter() {
        XList<String> actual = XList.of("a", "b", "c");
        String joined = actual.join("-");
        String expected = "a-b-c";

        assertThat(joined).isEqualTo(expected);
    }

    @Test
    public void shouldJoinElements_withoutDelimiter() {
        XList<String> actual = XList.of("a", "b", "c");
        String joined = actual.join();
        String expected = "abc";

        assertThat(joined).isEqualTo(expected);
    }

    @Test
    public void applyFunctionOnCollection_multiply() {
        XList<Integer> givenList = XList.of(1, 2, 3, 4);

        XList<Integer> actual = givenList.collect(e -> e * 2);

        assertThat(actual).containsOnly(2, 4, 6, 8);
    }

    @Test
    public void applyFunctionOnCollection_joinWithDelimiter() {
        XList<XList<String>> xList = XList.of(XList.of("ab"), XList.of("cd"), XList.of("ef"));
        XList<String> actual = xList.collect(e -> e.join("-"));

        assertThat(actual).containsOnly("a-b", "c-d", "e-f");
    }

}