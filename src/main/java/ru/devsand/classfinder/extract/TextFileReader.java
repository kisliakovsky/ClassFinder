package ru.devsand.classfinder.extract;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileReader implements TextSupplier {

    private final List<String> lines;

    public TextFileReader(Path filePath) throws IOException {
        this.lines = readLines(filePath);
    }

    private static List<String> readLines(Path filePath) throws IOException {
        try (final Stream<String> lineStream = Files.lines(filePath)) {
            return lineStream.filter(s -> !s.isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<String> get() {
        return lines;
    }

}
