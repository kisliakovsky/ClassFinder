package ru.devsand.classfinder.pattern;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleClassNamePatternTest {

    @Test
    public void checkPatternMustIncludeClassNameLettersInRightOrder() {
        assertThat(new SimpleClassNamePattern("FB").match("FooBarBaz"), is(true));
        assertThat(new SimpleClassNamePattern("FoBa").match("FooBarBaz"), is(true));
        assertThat(new SimpleClassNamePattern("FBar").match("FooBarBaz"), is(true));
        assertThat(new SimpleClassNamePattern("FB").match("FooBar"), is(true));
        assertThat(new SimpleClassNamePattern("FoBa").match("FooBar"), is(true));
        assertThat(new SimpleClassNamePattern("FBar").match("FooBar"), is(true));
        assertThat(new SimpleClassNamePattern("BF").match("FooBar"), is(false));
    }

    @Test
    public void checkPatternMustMatchClassNamesCaseInsensitively() {
        assertThat(new SimpleClassNamePattern("fbb").match("FooBarBaz"), is(true));
        assertThat(new SimpleClassNamePattern("fBb").match("FooBarBaz"), is(false));
    }

    @Test
    public void checkPatternWithTrailingSpaceMustContainLastWord() {
        assertThat(new SimpleClassNamePattern("FBar ").match("FooBar"), is(true));
        assertThat(new SimpleClassNamePattern("FBar ").match("FooBarBaz"), is(false));
    }

    @Test
    public void checkPatternWithWildcardMustMatchMissingLetters() {
        assertThat(new SimpleClassNamePattern("B*rBaz").match("FooBarBaz"), is(true));
        assertThat(new SimpleClassNamePattern("B*rBaz").match("BrBaz"), is(false));
    }

}