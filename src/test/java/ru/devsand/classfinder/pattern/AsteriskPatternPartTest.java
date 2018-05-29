package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.pattern.AsteriskPatternPart.from;

@RunWith(Parameterized.class)
public class AsteriskPatternPartTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
//                {from("abr*acad*r*a"), "abrabcdacadabcdrabca", 0},
//                {from("abr*acad*ra"), "abrabcdacadabcdra", 0},
//                {from("abr**ra"), "abrabcdacadabcdra", 0},
//                {from("*ra"), "abcra", 0},
                {from("ra*"), "raabc", 0},
//                {from("*ra*"), "abcrabfgfg", 0},
//                {from("*ra**"), "rabfgfg", 0},
//                {from("abr*acad*r*a"), "abrabcdabcdrabca", -1},
//                {from("abr*acad*ra"), "abrabcdacadabcdr", -1},
//                {from("abr**ra"), "brabcdacadabcdra", -1},
//                {from("*ra"), "abcr", -1},
//                {from("*ra*"), "abcrbfgfg", -1},
//                {from("*ra**"), "abcabfgfg", -1},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public AsteriskPatternPart patternPart;

    @Parameterized.Parameter(value = 1)
    public String s;

    @Parameterized.Parameter(value = 2)
    public int expectedResult;

    @Test
    public void checkCompareToString() {
        assertThat(patternPart.compareToString(s), is(expectedResult));
    }

}