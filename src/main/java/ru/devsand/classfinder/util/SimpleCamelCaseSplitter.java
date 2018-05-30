package ru.devsand.classfinder.util;

import java.util.List;

import static java.lang.Character.isDigit;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;

public class SimpleCamelCaseSplitter extends RemainedDelimiterStringSplitter implements SpecialStringSplitter<String> {

    private RemainedDelimiterStringSplitter innerSplitter = new RemainedDelimiterStringSplitter();

    @Override
    public List<String> apply(String string) {
        List<String> splits = innerSplitter.apply(string, SimpleCamelCaseSplitter::isDelimiter);
        if (isDelimiter(string.charAt(0))) {
            splits.remove(0);
        }
        return splits;
    }

    private static boolean isDelimiter(char c) {
        return isCapitalLetter(c) || isDigit(c);
    }

}
