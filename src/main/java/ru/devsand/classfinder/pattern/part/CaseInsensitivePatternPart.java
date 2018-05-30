package ru.devsand.classfinder.pattern.part;

class CaseInsensitivePatternPart extends AbstractPatternPart {

    CaseInsensitivePatternPart(String patternPart) {
        super(patternPart);
    }

    @Override
    public int compareToString(String s) {
        return patternPart.toLowerCase().compareTo(s.toLowerCase());
    }

}
