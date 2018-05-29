package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.CamelCaseUtil.splitCamelCase;

@RunWith(Parameterized.class)
public class SplitCamelCaseTest {
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FooBarBaz", Arrays.asList("Foo", "Bar", "Baz")},
                {"FB", Arrays.asList("F", "B")},
                {"FBar", Arrays.asList("F", "Bar")},
                {"B*rBaz", Arrays.asList("B*r", "Baz")}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String string;

    @Parameterized.Parameter(value = 1)
    public List<String> splitString;

    @Test
    public void checkSplit() {
        assertThat(splitCamelCase(string), is(splitString));
    }
}