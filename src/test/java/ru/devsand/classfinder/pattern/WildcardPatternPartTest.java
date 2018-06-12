package ru.devsand.classfinder.pattern;

import org.junit.Test;
import ru.devsand.classfinder.pattern.part.PatternPart;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WildcardPatternPartTest {

    private static final char WILDCARD = '*';

    @Test
    public void checkWildcardMustEqualToMissingLetters() {
        assertThat(PatternPart.from("*bcd*fgh*jkl*nop*", WILDCARD)
                .compareToString("abcdefghijklmnopq"), equalTo(0));
        assertThat(PatternPart.from("*bcd*fgh*jkl*nop*", WILDCARD)
                .compareToString("aabcdeefghiiiijklmmmmmnopq"), equalTo(0));
        assertThat(PatternPart.from("abc*efg", WILDCARD)
                .compareToString("abcdefg"), equalTo(0));
        assertThat(PatternPart.from("*", WILDCARD)
                .compareToString("abcefg"), equalTo(0));

    }

    @Test
    public void checkUnequalComparisonIsAlphabetic() {
        assertThat(PatternPart.from("*bcd*fgh*jkl*nop*", WILDCARD)
                .compareToString("bcdefghijklmnopq"), equalTo(-1));
        assertThat(PatternPart.from("*bcd*fgh*jkl*nop*", WILDCARD)
                .compareToString("abcdefghijklmnop"), equalTo(-1));
        assertThat(PatternPart.from("abc*efg", WILDCARD)
                .compareToString("abcefg"), equalTo(-1));
        assertThat(PatternPart.from("bcd*fgh*jkl*nop", WILDCARD)
                .compareToString("abcefg"), equalTo(1));
        assertThat(PatternPart.from("bcd*fgh*jkl*nop*", WILDCARD)
                .compareToString("abcefg"), equalTo(1));
    }

    @Test
    public void checkExtraWildcardsAreProcessedAsOne() {
        assertThat(PatternPart.from("abc**efg", WILDCARD)
                .compareToString("abcdefg"), equalTo(0));
        assertThat(PatternPart.from("**", WILDCARD)
                .compareToString("abcefg"), equalTo(0));
        assertThat(PatternPart.from("***bcd**fgh****jkl*nop**", WILDCARD)
                .compareToString("abcdefghijklmnopq"), equalTo(0));
    }

}