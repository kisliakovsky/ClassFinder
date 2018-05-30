package ru.devsand.classfinder.util;

import java.util.List;
import java.util.function.Predicate;

class RemainedDelimiterStringSplitter {

    List<String> apply(String string, Predicate<Character> delimiterChecker) {
        return StringUtil.split(string, delimiterChecker, true);
    }

}
