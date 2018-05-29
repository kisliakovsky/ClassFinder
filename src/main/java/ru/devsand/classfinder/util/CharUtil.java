package ru.devsand.classfinder.util;

public class CharUtil {

    // Suppress default constructor for noninstantiability
    private CharUtil() {
        throw new AssertionError();
    }

    public static boolean isCapitalLetter(char c) {
        return Character.isLetter(c) && Character.isUpperCase(c);
    }

}
