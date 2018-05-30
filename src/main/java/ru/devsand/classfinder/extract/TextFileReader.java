package ru.devsand.classfinder.extract;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class TextFileReader implements TextReader {

    private final Path filePath;
    private final Collection<String> lines;

    public TextFileReader(Path filePath) {
        this.filePath = filePath;
        this.lines = new ArrayList<>();
    }

    @Override
    public Collection<String> get() {
        return lines;
    }

}
