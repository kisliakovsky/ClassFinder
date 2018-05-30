package ru.devsand.classfinder.pattern.part;

class CaseInsensitivePatternPart extends PlainPatternPart {

    CaseInsensitivePatternPart(String patternPart) {
        super(patternPart.toLowerCase());
    }

    @Override
    public int compareToString(String s) {
        return super.compareToString(s.toLowerCase());
    }

}
