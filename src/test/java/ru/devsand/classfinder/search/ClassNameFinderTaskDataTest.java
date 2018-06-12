package ru.devsand.classfinder.search;

import org.junit.Test;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassNameFinderTaskDataTest {

    private static ClassNameFinder classNameFinder = new SimpleClassNameFinder(
                () -> Arrays.asList("a.b.FooBarBaz", "c.d.FooBar", "BrBaz"));

    @Test
    public void checkFinderMustFindAllClassNamesWhereLettersAreInRightOrder() {
        assertThat(classNameFinder.find("FB"),
                equalTo(Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")));
        assertThat(classNameFinder.find("FoBa"),
                equalTo(Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")));
        assertThat(classNameFinder.find("FBar"),
                equalTo(Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")));
        assertThat(classNameFinder.find("BF"),
                equalTo(emptyList()));
        assertThat(classNameFinder.find("fBb"),
                equalTo(emptyList()));
        assertThat(classNameFinder.find("abfkjlfgs"),
                equalTo(emptyList()));
    }

    @Test
    public void checkFinderMustFindAllClassNamesCaseInsensitively() {
        assertThat(classNameFinder.find("fbb"), equalTo(singletonList("a.b.FooBarBaz")));
    }

    @Test
    public void checkFinderMustFindAllClassNamesByLastWord() {
        assertThat(classNameFinder.find("FBar "),
                equalTo(singletonList("c.d.FooBar")));
    }

    @Test
    public void checkFinderMustFindAllClassNamesWithMissingLetters() {
        assertThat(classNameFinder.find("B*rBaz"),
                equalTo(singletonList("a.b.FooBarBaz")));
    }

}