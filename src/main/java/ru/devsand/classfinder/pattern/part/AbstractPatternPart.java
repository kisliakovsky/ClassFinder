package ru.devsand.classfinder.pattern.part;

public abstract class AbstractPatternPart implements PatternPart {

    final String patternPart;

    AbstractPatternPart(String patternPart) {
        this.patternPart = patternPart;
    }

    @Override
    public String toString() {
        return patternPart;
    }

}
