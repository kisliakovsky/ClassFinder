package ru.devsand.classfinder.util;

import java.util.List;

import static java.lang.Character.isDigit;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;

public class CamelCaseUtil {

    // Suppress default constructor for noninstantiability
    private CamelCaseUtil() {
        throw new AssertionError();
    }

    public static List<String> splitByCamelCaseDelimiter(String string) {
        return StringUtil.splitButRemainDelimiter(string, CamelCaseUtil::isCamelCaseDelimiter);
    }

    private static boolean isCamelCaseDelimiter(char c) {
        return isCapitalLetter(c) || isDigit(c);
    }

}
