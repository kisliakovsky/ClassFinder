package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CamelCasePatternTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new CamelCasePattern("FB"), "FooBar", true}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public CamelCasePattern pattern;

    @Parameterized.Parameter(value = 1)
    public String className;

    @Parameterized.Parameter(value = 2)
    public boolean matched;

    @Test
    public void checkMatch() {
        assertThat(pattern.match(className), is(matched));
    }

}