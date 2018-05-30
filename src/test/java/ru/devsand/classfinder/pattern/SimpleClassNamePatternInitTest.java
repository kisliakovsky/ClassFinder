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
                {new SimpleClassNamePattern("FB"), false, Arrays.asList("F", "B")},
                {new SimpleClassNamePattern("FoBa"), false, Arrays.asList("Fo", "Ba")},
                {new SimpleClassNamePattern("FBar"), false, Arrays.asList("F", "Bar")},
                {new SimpleClassNamePattern("FBar "), true, Arrays.asList("F", "Bar")}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public SimpleClassNamePattern pattern;

    @Parameterized.Parameter(value = 1)
    public boolean presenceOfLastWord;

    @Parameterized.Parameter(value = 2)
    public List<String> parts;

    @Test
    public void checkNewInstances() {
        assertThat(pattern.containsLastWord(), is(presenceOfLastWord));
        assertThat(pattern.getPatternParts().stream()
                .map(Object::toString)
                .collect(Collectors.toList()), is(parts));
    }

}