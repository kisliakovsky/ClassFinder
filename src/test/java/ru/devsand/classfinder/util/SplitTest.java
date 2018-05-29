package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.StringUtil.split;

@RunWith(Parameterized.class)
public class SplitTest {

    private static final Predicate<Character> DEFAULT_PREDICATE = (c) -> c == '*';

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"*ab*racad*abra*", Arrays.asList("", "ab", "racad", "abra", "")},
                {"*", Arrays.asList("", "")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String s;

    @Parameterized.Parameter(value = 1)
    public boolean splitString;

    @Test
    public void checkIfLowerCase() {
        assertThat(split(s, DEFAULT_PREDICATE), is(splitString));
    }

}