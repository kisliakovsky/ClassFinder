package ru.devsand.classfinder.util;

import java.util.List;
import java.util.function.Predicate;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;
import static ru.devsand.classfinder.util.CharUtil.isCapitalLetter;

public class StringSplitters {

    // Suppress default constructor for noninstantiability
    private StringSplitters() {
        throw new AssertionError();
    }

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

    static class SimpleCamelCaseSplitter extends RemainedDelimiterStringSplitter
            implements SpecialStringSplitter<String> {

        private RemainedDelimiterStringSplitter innerSplitter = new RemainedDelimiterStringSplitter();

        @Override
        public List<String> apply(String string) {
            List<String> splits = innerSplitter.apply(string, SimpleCamelCaseSplitter::isDelimiter);
            if (!string.isEmpty() && isDelimiter(string.charAt(0))) {
                splits.remove(0);
            }
            return splits;
        }

        private static boolean isDelimiter(char c) {
            return isCapitalLetter(c) || isDigit(c);
        }

    }

    static class SimpleStringSplitter {

        List<String> apply(String string, char delimiter) {
            return StringUtil.split(string, c -> c == delimiter, false);
        }

    }

    static class RemainedDelimiterStringSplitter {

        List<String> apply(String string, Predicate<Character> delimiterChecker) {
            return StringUtil.split(string, delimiterChecker, true);
        }

    }

}
