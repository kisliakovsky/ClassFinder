package ru.devsand.classfinder.core;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class TextFileClassFinder implements ClassFinder {

    private final Path filePath;
    private final String pattern;

    public TextFileClassFinder(Path filePath, String pattern) {
        this.filePath = filePath;
        this.pattern = pattern;
    }

    @Override
    public List<String> getClassNames() {
        return Collections.emptyList();
    }

}
