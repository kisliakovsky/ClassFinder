package ru.devsand.classfinder.util;

class CharUtil {

    // Suppress default constructor for noninstantiability
    private CharUtil() {
        throw new AssertionError();
    }

    static boolean isCapitalLetter(char c) {
        return Character.isLetter(c) && Character.isUpperCase(c);
    }

}
