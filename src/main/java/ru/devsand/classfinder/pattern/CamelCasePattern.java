package ru.devsand.classfinder.pattern;

import java.util.List;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;
import static ru.devsand.classfinder.util.CamelCaseUtil.splitByCamelCaseDelimiter;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;
import static ru.devsand.classfinder.util.StringUtil.isLowerCase;

public class CamelCasePattern implements ClassNamePattern {

    private int hashCode;
    private final String pattern;
    private final boolean lastWordFlag;
    private final List<String> patternParts;

    public CamelCasePattern(String pattern) {
        this.pattern = pattern;
        this.lastWordFlag = containsLastWord(pattern);
        this.patternParts = splitPattern(pattern.trim());
    }

    private static boolean containsLastWord(String pattern) {
        return pattern.endsWith(" ");
    }

    private static List<String> splitPattern(String pattern) {
        if (isLowerCase(pattern)) {
            return splitPatternByLetter(pattern);
        } else {
            return splitByCamelCaseDelimiter(pattern);
        }
    }

    private static List<String> splitPatternByLetter(String pattern) {
        return pattern.chars().mapToObj(c -> String.valueOf((char) c)).collect(toList());
    }

    private static boolean isDelimiter(char c) {
        return isCapitalLetter(c) || isDigit(c);
    }

    public String getPattern() {
        return pattern;
    }

    public boolean containsLastWord() {
        return lastWordFlag;
    }

    public List<String> getPatternParts() {
        return patternParts;
    }

    @Override
    public boolean match(String className) {
        final List<String> classNameParts = splitByCamelCaseDelimiter(className);
        return false;
    }

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }
        if (!(another instanceof CamelCasePattern)) {
            return false;
        }
        final CamelCasePattern camelCasePattern = (CamelCasePattern) another;
        return camelCasePattern.pattern.equals(pattern);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = pattern.hashCode();
            result = 31 * result + Integer.hashCode(patternParts.size());
            hashCode = result;
        }
        return result;
    }

    @Override
    public String toString() {
        return pattern;
    }
}
