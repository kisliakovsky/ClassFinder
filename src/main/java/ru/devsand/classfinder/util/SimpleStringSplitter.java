package ru.devsand.classfinder.util;

import java.util.List;

public class SimpleStringSplitter {

    public List<String> apply(String string, char delimiter) {
        return StringUtil.split(string, c -> c == delimiter, false);
    }

}
