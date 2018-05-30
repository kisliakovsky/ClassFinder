package ru.devsand.classfinder.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.devsand.classfinder.pattern.part.PatternPart;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.devsand.classfinder.pattern.part.PatternPart.from;

@RunWith(Parameterized.class)
public class WildcardPatternPartTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {from("*bcd*fgh*jkl*nop*", '*'), "abcdefghijklmnopq", 0},
                {from("*bcd*fgh*jkl*nop*", '*'), "aabcdeefghiiiijklmmmmmnopq", 0},
                {from("abc*efg", '*'), "abcdefg", 0},
                {from("*bcd*fgh*jkl*nop*", '*'), "bcdefghijklmnopq", -1},
                {from("*bcd*fgh*jkl*nop*", '*'), "abcdefghijklmnop", -1},
                {from("abc*efg", '*'), "abcefg", -1},
                {from("*", '*'), "abcefg", 0},
                {from("**", '*'), "abcefg", 0},
                {from("*bcd*fgh*jkl*nop*", '*'), "abcefg", -1},
                {from("bcd*fgh*jkl*nop", '*'), "abcefg", 1},
                {from("bcd*fgh*jkl*nop*", '*'), "abcefg", 1},
                {from("*bcd*fgh*jkl*nop", '*'), "abcefg", -1},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public PatternPart patternPart;

    @Parameterized.Parameter(value = 1)
    public String s;

    @Parameterized.Parameter(value = 2)
    public int expectedResult;

    @Test
    public void checkCompareToString() {
        assertThat(patternPart.compareToString(s), is(expectedResult));
    }

}