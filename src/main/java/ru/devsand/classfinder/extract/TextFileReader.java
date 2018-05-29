package ru.devsand.classfinder.extract;

import java.nio.file.Path;
import java.util.Collection;

public class TextFileReader implements TextReader {

    private final Path filePath;
    private final Collection<String> words;

    public TextFileReader(Path filePath) {
        this.filePath = filePath;
        this.words = null;
    }

    @Override
    public Collection<String> get() {
        return words;
    }

}
