package com.smola;

import com.smola.xlist.XList;
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
    public void applyFunctionOnSimpleCollection() {
        XList<String> xList = XList.of("a", "b", "c", "d");
        XList<String> expected = XList.of("a-b-c-d");

        XList<String> a = xList.collect(xList::join);
    }

    @Test
    public void applyFunctionOnCollection_joinWithDelimiter() {
        XList<XList<String>> xList = XList.of(XList.of("ab", "cd"), XList.of("ef", "gh"), XList.of("ij", "kl", "on"));
        XList<String> actual = xList.collect(e -> e.join("-"));

        assertThat(actual).containsOnly("ab-cd", "ef-gh", "ij-kl-on");
    }

    @Test
    public void forEachWithIndex_withSimpleMapping() {
        XList<Integer> lmod = XList.of(1, 2, 8, 10, 11, 30, 3, 4);
        lmod.forEachWithIndex((e, i) -> lmod.set(i, e * 2));

        lmod.collect(e->e+ "s");
        XList<Integer> expected = XList.of(2, 4, 16, 20, 22, 60, 6, 8);

        assertEquals(lmod, expected);
    }

    @Test
    public void forEach_withEvenIndex() {
        XList<Integer> lmod = XList.of(2, 4, 16, 20, 22, 60, 6, 8);
        lmod.forEachWithIndex((e, i) -> {
            if (i % 2 == 0) lmod.remove(e);
        });

        XList<Integer> expected = XList.of(4, 16, 22, 60, 8);

        assertEquals(lmod,expected);
    }

    @Test
    public void sd() {
        XList<Number> of = XList.of(Arrays.asList(1, 2.5));
    }
}