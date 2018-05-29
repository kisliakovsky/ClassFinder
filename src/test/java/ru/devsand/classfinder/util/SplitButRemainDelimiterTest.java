package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.StringUtil.split;
import static ru.devsand.classfinder.util.StringUtil.splitButRemainDelimiter;

@RunWith(Parameterized.class)
public class SplitButRemainDelimiterTest {

    private static final Predicate<Character> DEFAULT_PREDICATE = (c) -> c == '*';

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"*ab*racad*abra*", Arrays.asList("", "*ab", "*racad", "*abra", "*")},
                {"*", Arrays.asList("", "*")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String s;

    @Parameterized.Parameter(value = 1)
    public List<String> splitString;

    @Test
    public void checkSplit() {
        assertThat(splitButRemainDelimiter(s, DEFAULT_PREDICATE), is(splitString));
    }

}