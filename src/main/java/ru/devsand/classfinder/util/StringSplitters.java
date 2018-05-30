package ru.devsand.classfinder.util;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class StringSplitters {

    private static final SpecialStringSplitter<String> LETTER_SPLITTER = pattern -> pattern.chars()
            .mapToObj(c -> String.valueOf((char) c))
            .collect(toList());
    private static final SpecialStringSplitter<String> CAMEL_CASE_SPLITTER =
            new SimpleCamelCaseSplitter();
    private static final SimpleStringSplitter SIMPLE_SPLITTER = new SimpleStringSplitter();

    public static List<String> splitByLetters(String string) {
        return LETTER_SPLITTER.apply(string);
    }

    public static List<String> splitAsCamelCase(String string) {
        return CAMEL_CASE_SPLITTER.apply(string);
    }

    public static List<String> split(String string, char delimiter) {
        return SIMPLE_SPLITTER.apply(string, delimiter);
    }

}
