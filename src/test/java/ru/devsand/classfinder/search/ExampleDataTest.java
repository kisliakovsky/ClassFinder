package ru.devsand.classfinder.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class ExampleDataTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FB", Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")},
                {"FoBa", Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")},
                {"FBar", Arrays.asList("c.d.FooBar", "a.b.FooBarBaz")},
                {"BF", Collections.emptyList()},
                {"fbb", Collections.singletonList("a.b.FooBarBaz")},
                {"fBb", Collections.emptyList()},
                {"FBar ", Collections.singletonList("c.d.FooBar")},
                {"B*rBaz", Collections.singletonList("a.b.FooBarBaz")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String pattern;

    @Parameterized.Parameter(value = 1)
    public List<String> remainedClasses;


    private ClassNameFinder classNameFinder;

    @Before
    public void setUp() {
        classNameFinder = new SimpleClassNameFinder(
                () -> Arrays.asList("a.b.FooBarBaz", "c.d.FooBar", "BrBaz"));
    }

    @Test
    public void checkFind() {
        assertThat(classNameFinder.find(pattern), is(remainedClasses));
    }
}