package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.StringUtil.split;

@RunWith(Parameterized.class)
public class SplitTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"*ab*racad*abra*", '*', Arrays.asList("", "ab", "racad", "abra", "")},
                {"abr" + File.separator + "acad" + File.separator + "abra",
                        File.separator.charAt(0),
                        Arrays.asList("abr", "acad", "abra")},
                {"ab.racad.abra", '.', Arrays.asList("ab", "racad", "abra")},
                {"*", '*', Arrays.asList("", "")},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String s;

    @Parameterized.Parameter(value = 1)
    public char delimiter;

    @Parameterized.Parameter(value = 2)
    public List<String> splitString;

    @Test
    public void checkSplit() {
        assertThat(split(s, c -> c == delimiter), is(splitString));
    }

}