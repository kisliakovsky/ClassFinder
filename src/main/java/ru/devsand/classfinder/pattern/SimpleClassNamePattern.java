package ru.devsand.classfinder.pattern;

import ru.devsand.classfinder.pattern.part.PatternPart;

import java.util.ArrayList;
import java.util.List;

import static ru.devsand.classfinder.util.StringSplitters.splitAsCamelCase;

public class SimpleClassNamePattern implements ClassNamePattern {

    private static final PatternCamelCaseSplitter patternSplitter = new PatternCamelCaseSplitter();

    private int hashCode;
    private final String pattern;
    private final boolean emptyPatternFlag;
    private final boolean lastWordFlag;
    private final List<PatternPart> patternParts;

    public SimpleClassNamePattern(String pattern) {
        this.pattern = pattern;
        this.emptyPatternFlag = pattern.isEmpty();
        this.lastWordFlag = containsLastWord(pattern);
        this.patternParts = patternSplitter.apply(pattern.trim());
    }

    private static boolean containsLastWord(String pattern) {
        return pattern.endsWith(" ");
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean match(String className) {
        if (emptyPatternFlag) {
            return false;
        }
        List<String> classNameParts = splitAsCamelCase(className);
        List<PatternPart> patternParts = copyList(this.patternParts);
        if (lastWordFlag) {
            return matchPartsStartingFromLast(patternParts, classNameParts);
        } else {
            return matchParts(patternParts, classNameParts);
        }
    }

    private static <T> List<T> copyList(List<T> list) {
        return new ArrayList<>(list);
    }

    private boolean matchPartsStartingFromLast(List<PatternPart> patternParts,
                                               List<String> classNameParts) {
        int lastPatternPartIndex = patternParts.size() - 1;
        PatternPart lastPatternPart = patternParts.get(lastPatternPartIndex);
        int lastClassNamePartIndex = classNameParts.size() - 1;
        String lastClassName = classNameParts.get(lastClassNamePartIndex);
        if (lastPatternPart.compareToString(lastClassName) == 0) {
            patternParts.remove(lastPatternPartIndex);
            classNameParts.remove(lastClassNamePartIndex);
            return matchParts(patternParts, classNameParts);
        } else {
            return false;
        }
    }

    private boolean matchParts(List<PatternPart> patternParts, List<String> classNameParts) {
        if (patternParts.isEmpty()) {
            return true;
        } else if (classNameParts.isEmpty()) {
            return false;
        } else {
            PatternPart firstPatternPart = patternParts.get(0);
            String firstClassNamePart = classNameParts.get(0);
            if (firstPatternPart.compareToString(firstClassNamePart) == 0) {
                patternParts.remove(0);
                classNameParts.remove(0);
                return matchParts(patternParts, classNameParts);
            } else {
                classNameParts.remove(0);
                return matchParts(patternParts, classNameParts);
            }
        }
    }

    public boolean containsLastWord() {
        return lastWordFlag;
    }

    public List<PatternPart> getPatternParts() {
        return copyList(patternParts);
    }

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }
        if (!(another instanceof SimpleClassNamePattern)) {
            return false;
        }
        final SimpleClassNamePattern simpleClassNamePattern = (SimpleClassNamePattern) another;
        return simpleClassNamePattern.pattern.equals(pattern);
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
