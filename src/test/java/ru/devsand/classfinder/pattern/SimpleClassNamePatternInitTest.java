package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SimpleClassNamePatternInitTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"FB", false, Arrays.asList("F", "B")},
                {"FoBa", false, Arrays.asList("Fo", "Ba")},
                {"FBar", false, Arrays.asList("F", "Bar")},
                {"FBar ", true, Arrays.asList("F", "Bar")}
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
        final SimpleClassNamePattern classNamePattern = new SimpleClassNamePattern(pattern);
        assertThat(classNamePattern.getPattern(), is(pattern));
        assertThat(classNamePattern.containsLastWord(), is(presenceOfLastWord));
        assertThat(classNamePattern.getPatternParts().stream()
                .map(Object::toString)
                .collect(Collectors.toList()), is(parts));
    }

}