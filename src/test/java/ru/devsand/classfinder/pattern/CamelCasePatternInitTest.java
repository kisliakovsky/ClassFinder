package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CamelCasePatternInitTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FB", true, Arrays.asList("F", "B")},
                {"FoBa", true, Arrays.asList("Fo", "Ba")},
                {"FBar", true, Arrays.asList("F", "Bar")}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String pattern;

    @Parameterized.Parameter(value = 1)
    public boolean presenceOfLastWord;

    @Parameterized.Parameter(value = 2)
    public List<String> parts;

    @Test
    public void checkNewInstances() {

    }

}