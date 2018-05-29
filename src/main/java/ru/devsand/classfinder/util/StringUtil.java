package ru.devsand.classfinder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringUtil {

    // Suppress default constructor for noninstantiability
    private StringUtil() {
        throw new AssertionError();
    }

    public static boolean isLowerCase(String string) {
        return string.equals(string.toLowerCase());
    }

    public static List<String> split(String string, Predicate<Character> delimiterChecker) {
        return split(string, delimiterChecker, false);
    }

    public static List<String> splitButRemainDelimiter(String string,
                                                       Predicate<Character> delimiterChecker) {
        return split(string, delimiterChecker, true);
    }

    private static List<String> split(String string, Predicate<Character> delimiterChecker,
                                     boolean remainDelimiter) {
        final List<String> parts = new ArrayList<>();
        StringBuilder partBuilder = new StringBuilder();
        for (char c: string.toCharArray()) {
            if (delimiterChecker.test(c)) {
                String stringPart = partBuilder.toString();
                parts.add(stringPart);
                clearStringBuilder(partBuilder);
                if (remainDelimiter) {
                    partBuilder.append(c);
                }
            } else {
                partBuilder.append(c);
            }
        }
        String stringPart = partBuilder.toString();
        parts.add(stringPart);
        return parts;
    }

    private static void clearStringBuilder(StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
    }

    public static String replaceAll(String s, String replaceable, String replacement) {
        if (replaceable.equals(replacement)) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder(s);
        int replaceableLength = replaceable.length();
        int startIndex = stringBuilder.indexOf(replaceable);
        while (startIndex != -1) {
            int endIndex = startIndex + replaceableLength;
            stringBuilder.replace(startIndex, endIndex, replacement);
            startIndex = stringBuilder.indexOf(replaceable);
        }
        return stringBuilder.toString();
    }

}
