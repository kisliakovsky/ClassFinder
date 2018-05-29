package ru.devsand.classfinder.util;

import java.util.List;

import static java.lang.Character.isDigit;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;
import static ru.devsand.classfinder.util.StringUtil.splitButRemainDelimiter;

public class CamelCaseUtil {

    // Suppress default constructor for noninstantiability
    private CamelCaseUtil() {
        throw new AssertionError();
    }

    public static List<String> splitCamelCase(String string) {
        final List<String> parts = splitByCamelCaseDelimiter(string);
        parts.remove(0);
        return parts;
    }

    private static List<String> splitByCamelCaseDelimiter(String string) {
        return splitButRemainDelimiter(string, CamelCaseUtil::isCamelCaseDelimiter);
    }

    private static boolean isCamelCaseDelimiter(char c) {
        return isCapitalLetter(c) || isDigit(c);
    }

}
