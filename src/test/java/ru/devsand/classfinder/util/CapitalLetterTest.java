package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;

@RunWith(Parameterized.class)
public class CapitalLetterTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {'A', true},
                {'Г', true},
                {'b', false},
                {'1', false},
                {'+', false}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public char c;

    @Parameterized.Parameter(value = 1)
    public boolean accordance;

    @Test
    public void checkIfCapitalLetter() {
        assertThat(isCapitalLetter(c), is(accordance));
    }

}