package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SimpleClassNamePatternTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FB", "FooBar", true},
                {"aFB", "antiFooBar", true},
                {"FB", "FooBarBaz", true},
                {"FoBa", "FooBarBaz", true},
                {"FBar", "FooBarBaz", true},
                {"FoBa", "FooBar", true},
                {"FBar", "FooBar", true},
                {"BF", "FooBar", false},
                {"fbb", "FooBarBaz", true},
                {"fBb", "FooBarBaz", false},
                {"FBar ", "FooBar", true},
                {"FBar ", "FooBarBaz", false},
                {"B*rBaz", "FooBarBaz", true},
                {"B*rBaz", "BrBaz", false},
                {"", "BrBaz", false},
                {"SomeText", "FooBarBaz", false},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String pattern;

    @Parameterized.Parameter(value = 1)
    public String className;

    @Parameterized.Parameter(value = 2)
    public boolean matched;

    @Test
    public void checkMatch() {
        assertThat(new SimpleClassNamePattern(pattern).match(className), is(matched));
    }

}