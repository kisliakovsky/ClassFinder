package ru.devsand.classfinder.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.util.StringUtil.isLowerCase;

@RunWith(Parameterized.class)
public class LowerCaseTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"abracadabra", true},
                {"aBracAdabra", false},
                {"язык", true},
                {"Character", false},
                {"dfdg4gfgh", true},
                {"1", true},
                {"", true}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String s;

    @Parameterized.Parameter(value = 1)
    public boolean expectedResult;

    @Test
    public void checkIfLowerCase() {
        assertThat(isLowerCase(s), is(expectedResult));
    }

}