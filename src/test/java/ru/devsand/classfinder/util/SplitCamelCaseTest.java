package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SplitCamelCaseTest {

    private static final SpecialStringSplitter<String> SPLITTER = new SimpleCamelCaseSplitter();

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FooBarBaz", Arrays.asList("Foo", "Bar", "Baz")},
                {"FB", Arrays.asList("F", "B")},
                {"FBar", Arrays.asList("F", "Bar")},
                {"B*rBaz", Arrays.asList("B*r", "Baz")},
                {"B", singletonList("B")},
                {"", singletonList("")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String string;

    @Parameterized.Parameter(value = 1)
    public List<String> splitString;

    @Test
    public void checkSplit() {
        assertThat(SPLITTER.apply(string), is(splitString));
    }
}