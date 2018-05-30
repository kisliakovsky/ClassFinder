package ru.devsand.classfinder.pattern.part;

public interface PatternPart {

    int compareToString(String s);

    static PatternPart newCaseInsensitiveInstance(String patternPart) {
        return new CaseInsensitivePatternPart(patternPart);
    }

    static PatternPart from(String patternPart) {
        return new PlainPatternPart(patternPart);
    }

    static PatternPart from(String patternPart, char wildcard) {
        if (patternPart.indexOf(wildcard) == -1) {
            return new PlainPatternPart(patternPart);
        } else {
            return new WildcardPatternPart(patternPart, wildcard);
        }
    }

}
