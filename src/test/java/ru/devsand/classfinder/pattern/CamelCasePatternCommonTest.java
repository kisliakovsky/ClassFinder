package ru.devsand.classfinder.pattern;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CamelCasePatternCommonTest {

    private static final String PATTERN = "FoBa";
    private static final String OTHER_PATTERN = "FBar";
    private static final Supplier<ClassNamePattern> PATTERN_FACTORY =
            () -> new CamelCasePattern(PATTERN);
    private static final Function<Integer, List<ClassNamePattern>> PATTERN_GENERATOR =
            (n) -> IntStream.range(0, n)
                    .mapToObj(i -> PATTERN_FACTORY.get())
                    .collect(toList());

    @Test
    public void checkReflexivity() {
        final ClassNamePattern pattern = PATTERN_FACTORY.get();
        //noinspection EqualsWithItself
        assertThat(pattern.equals(pattern), is(true));
    }

    @Test
    public void checkSymmetry() {
        final List<ClassNamePattern> patterns = PATTERN_GENERATOR.apply(2);
        assertThat(patterns.get(0).equals(patterns.get(1)),
                is(patterns.get(1).equals(patterns.get(0))));
    }

    @Test
    public void checkTransitivity() {
        final List<ClassNamePattern> patterns = PATTERN_GENERATOR.apply(3);
        assertThat(patterns.get(0).equals(patterns.get(1)), is(true));
        assertThat(patterns.get(1).equals(patterns.get(2)), is(true));
        assertThat(patterns.get(0).equals(patterns.get(2)), is(true));
    }

    @Test
    public void checkNonNullity() {
        final ClassNamePattern pattern = PATTERN_FACTORY.get();
        //noinspection ConstantConditions,ObjectEqualsNull
        assertThat(pattern.equals(null), is(false));
    }

    @Test
    public void checkHashCodeForEquals() {
        final List<Integer> hashCodes = PATTERN_GENERATOR.apply(3).stream()
                .map(Object::hashCode)
                .collect(toList());
        assertThat(hashCodes.get(0), is(hashCodes.get(1)));
    }

    @Test
    public void checkHashCodeForNonEquals() {
        final int firstHashCode = PATTERN_FACTORY.get().hashCode();
        final int secondHashCode = new CamelCasePattern(OTHER_PATTERN).hashCode();
        assertThat(firstHashCode, is(not(secondHashCode)));
    }

    @Test
    public void checkToString() {
        final ClassNamePattern pattern = PATTERN_FACTORY.get();
        assertThat(pattern.toString(), is(PATTERN));
    }

}