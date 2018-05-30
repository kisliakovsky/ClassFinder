package ru.devsand.classfinder.pattern.part;

class PlainPatternPart extends AbstractPatternPart {

    PlainPatternPart(String patternPart) {
        super(patternPart);
    }

    @Override
    public int compareToString(String s) {
        return patternPart.compareTo(s);
    }

}
