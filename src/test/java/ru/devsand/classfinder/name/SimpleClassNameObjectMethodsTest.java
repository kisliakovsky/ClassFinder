package ru.devsand.classfinder.name;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleClassNameObjectMethodsTest {

    private static final String CLASS_NAME = "a.b.FooBar";
    private static final String OTHER_CLASS_NAME = "c.d.FooBarBaz";

    private static final Supplier<ClassName> CLASS_NAME_FACTORY =
            () -> new SimpleClassName(CLASS_NAME);
    private static final Function<Integer, List<ClassName>> CLASS_NAME_GENERATOR =
            (n) -> IntStream.range(0, n)
                    .mapToObj(i -> CLASS_NAME_FACTORY.get())
                    .collect(toList());

    @Test
    public void checkReflexivity() {
        final ClassName pattern = CLASS_NAME_FACTORY.get();
        //noinspection EqualsWithItself
        assertThat(pattern.equals(pattern), is(true));
    }

    @Test
    public void checkSymmetry() {
        final List<ClassName> patterns = CLASS_NAME_GENERATOR.apply(2);
        assertThat(patterns.get(0).equals(patterns.get(1)),
                is(patterns.get(1).equals(patterns.get(0))));
    }

    @Test
    public void checkTransitivity() {
        final List<ClassName> patterns = CLASS_NAME_GENERATOR.apply(3);
        assertThat(patterns.get(0).equals(patterns.get(1)), is(true));
        assertThat(patterns.get(1).equals(patterns.get(2)), is(true));
        assertThat(patterns.get(0).equals(patterns.get(2)), is(true));
    }

    @Test
    public void checkNonNullity() {
        final ClassName pattern = CLASS_NAME_FACTORY.get();
        //noinspection ConstantConditions,ObjectEqualsNull
        assertThat(pattern.equals(null), is(false));
    }

    @Test
    public void checkHashCodeForEquals() {
        final List<Integer> hashCodes = CLASS_NAME_GENERATOR.apply(3).stream()
                .map(Object::hashCode)
                .collect(toList());
        assertThat(hashCodes.get(0), is(hashCodes.get(1)));
    }

    @Test
    public void checkHashCodeForNonEquals() {
        final int firstHashCode = CLASS_NAME_FACTORY.get().hashCode();
        final int secondHashCode = new SimpleClassName(OTHER_CLASS_NAME).hashCode();
        assertThat(firstHashCode, is(not(secondHashCode)));
    }

    @Test
    public void checkToString() {
        final ClassName pattern = CLASS_NAME_FACTORY.get();
        assertThat(pattern.toString(), is(CLASS_NAME));
    }

}