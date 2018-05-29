package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CamelCasePatternInitTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new CamelCasePattern("FB"), false, Arrays.asList("F", "B")},
                {new CamelCasePattern("FoBa"), false, Arrays.asList("Fo", "Ba")},
                {new CamelCasePattern("FBar"), false, Arrays.asList("F", "Bar")},
                {new CamelCasePattern("FBar "), true, Arrays.asList("F", "Bar")}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public CamelCasePattern pattern;

    @Parameterized.Parameter(value = 1)
    public boolean presenceOfLastWord;

    @Parameterized.Parameter(value = 2)
    public List<String> parts;

    @Test
    public void checkNewInstances() {
        assertThat(pattern.containsLastWord(), is(presenceOfLastWord));
        assertThat(pattern.getPatternParts(), is(parts));
    }

}