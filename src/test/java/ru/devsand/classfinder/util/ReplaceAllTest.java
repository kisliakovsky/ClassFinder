package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.StringUtil.replaceAll;

@RunWith(Parameterized.class)
public class ReplaceAllTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"abracadabra", "a", "x", "xbrxcxdxbrx"},
                {"**ab**racad**abra**", "**", "*", "*ab*racad*abra*"},
                {"****", "**", "*", "*"},
                {"", "**", "*", ""},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String string;

    @Parameterized.Parameter(value = 1)
    public String replaceable;

    @Parameterized.Parameter(value = 2)
    public String replacement;

    @Parameterized.Parameter(value = 3)
    public String expectedString;

    @Test
    public void checkReplaceAll() {
        assertThat(replaceAll(string, replaceable, replacement), is(expectedString));
    }

}