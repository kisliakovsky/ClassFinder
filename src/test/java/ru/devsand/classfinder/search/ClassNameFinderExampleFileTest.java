package ru.devsand.classfinder.search;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.devsand.classfinder.extract.TextFileReader;
import ru.devsand.classfinder.extract.TextFileReaderTest;
import ru.devsand.classfinder.extract.TextSupplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassNameFinderExampleFileTest {

    private static ClassNameFinder classNameFinder;

    @BeforeClass
    public static void setUp() throws IOException {
        ClassLoader classLoader = TextFileReaderTest.class.getClassLoader();
        URL textFileUrl = classLoader.getResource("classes.txt");
        requireNonNull(textFileUrl);
        File file = new File(textFileUrl.getFile());
        Path filePath = Paths.get(file.getPath());
        TextSupplier classNamesSupplier = new TextFileReader(filePath);
        classNameFinder = new SimpleClassNameFinder(classNamesSupplier);
    }

    @Test
    public void checkFinderMustFindAllClassNamesWhereLettersAreInRightOrder() {
        assertThat(classNameFinder.find("M"),
                equalTo(Arrays.asList("codeborne.MindReader", "codeborne.WishMaker")));
        assertThat(classNameFinder.find("WM"),
                equalTo(singletonList("codeborne.WishMaker")));
        assertThat(classNameFinder.find("MaR"), equalTo(emptyList()));
        assertThat(classNameFinder.find("Op"),
                equalTo(Arrays.asList("ScubaArgentineOperator", "TelephoneOperator")));
        assertThat(classNameFinder.find("YA"),
                equalTo(singletonList("YourEyesAreSpinningInTheirSockets")));
        assertThat(classNameFinder.find("You"),
                equalTo(Arrays.asList("YourEyesAreSpinningInTheirSockets",
                        "YoureLeavingUsHere", "YouveComeToThisPoint")));
        assertThat(classNameFinder.find("NonSense"),
                equalTo(emptyList()));
    }

    @Test
    public void checkFinderMustFindAllClassNamesCaseInsensitively() {
        assertThat(classNameFinder.find("sao"), equalTo(singletonList("ScubaArgentineOperator")));
    }

    @Test
    public void checkFinderMustFindAllClassNamesByLastWord() {
        assertThat(classNameFinder.find("M "),
                equalTo(singletonList("codeborne.WishMaker")));
        assertThat(classNameFinder.find("YA "),
                equalTo(emptyList()));
        assertThat(classNameFinder.find("Op "),
                equalTo(Arrays.asList("ScubaArgentineOperator", "TelephoneOperator")));
        assertThat(classNameFinder.find("MaR "),
                equalTo(emptyList()));
    }

    @Test
    public void checkFinderMustFindAllClassNamesWithMissingLetters() {
        assertThat(classNameFinder.find("M*R"),
                equalTo(singletonList("codeborne.MindReader")));
        assertThat(classNameFinder.find("Ar*ne"),
                equalTo(singletonList("ScubaArgentineOperator")));
    }

    @Test
    public void checkFinderMustFindAllClassNamesByMixedPattern() {
        assertThat(classNameFinder.find("M*R"),
                equalTo(singletonList("codeborne.MindReader")));
        assertThat(classNameFinder.find("Op "),
                equalTo(Arrays.asList("ScubaArgentineOperator", "TelephoneOperator")));
        assertThat(classNameFinder.find("sao "),
                equalTo(singletonList("ScubaArgentineOperator")));
        assertThat(classNameFinder.find("YA "),
                equalTo(emptyList()));

    }

}
