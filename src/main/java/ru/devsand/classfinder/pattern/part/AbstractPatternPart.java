package ru.devsand.classfinder.pattern.part;

public abstract class AbstractPatternPart implements PatternPart {

    protected final String patternPart;

    public AbstractPatternPart(String patternPart) {
        this.patternPart = patternPart;
    }

    @Override
    public String toString() {
        return patternPart;
    }

}
