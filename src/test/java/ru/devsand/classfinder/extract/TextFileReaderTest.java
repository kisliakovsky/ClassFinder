package ru.devsand.classfinder.extract;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextFileReaderTest {

    @Test
    public void checkFirstLineMustBeReadRightFromTestFile() throws IOException {
        ClassLoader classLoader = TextFileReaderTest.class.getClassLoader();
        URL textFileUrl = classLoader.getResource("classes.txt");
        requireNonNull(textFileUrl);
        File file = new File(textFileUrl.getFile());
        Path filePath = Paths.get(file.getPath());
        TextSupplier supplier = new TextFileReader(filePath);
        List<String> words = supplier.get();
        assertThat(words.size(), is(not(0)));
        assertThat(words.get(0), is("a.b.FooBarBaz"));
    }

}