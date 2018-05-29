package ru.devsand.classfinder.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class ExampleDataTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FB", Arrays.asList("a.b.FooBarBaz", "c.d.FooBar")},
                {"FoBa", Arrays.asList("a.b.FooBarBaz", "c.d.FooBar")},
                {"FBar", Arrays.asList("a.b.FooBarBaz", "c.d.FooBar")},
                {"BF", Collections.emptyList()},
                {"fbb", Arrays.asList("a.b.FooBarBaz", "c.d.FooBar")},
                {"fBb", Collections.emptyList()},
                {"FBar ", Collections.singletonList("c.d.FooBar")},
                {"B*rBaz", Collections.singletonList("a.b.FooBarBaz")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String pattern;

    @Parameterized.Parameter(value = 1)
    public Collection<String> remainedClasses;


    private ClassNameFinder classNameFinder;

    @Before
    public void setUp() {
        classNameFinder = new SimpleClassNameFinder(
                () -> Arrays.asList("a.b.FooBarBaz", "c.d.FooBar", "BrBaz"));
    }

    @Test
    public void checkFind() {
        assertThat(classNameFinder.find(pattern).equals(remainedClasses), is(true));
    }
}