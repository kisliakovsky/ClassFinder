package ru.devsand.classfinder.pattern.part;

class PlainPatternPart extends AbstractPatternPart {

    PlainPatternPart(String patternPart) {
        super(patternPart);
    }

    @Override
    public int compareToString(String s) {
        if (s.startsWith(patternPart)) {
            return 0;
        } else {
            return (patternPart.compareTo(s));
        }
    }

}
