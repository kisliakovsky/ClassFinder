package ru.devsand.classfinder.core;

import java.nio.file.Path;
import java.util.List;

public interface ClassFinder {

    static ClassFinder forTextFile(Path filePath, String pattern) {
        return new TextFileClassFinder(filePath, pattern);
    }

    List<String> getClassNames();

}
