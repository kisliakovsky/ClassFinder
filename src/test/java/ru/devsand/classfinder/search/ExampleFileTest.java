package ru.devsand.classfinder.search;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.devsand.classfinder.extract.TextFileReader;
import ru.devsand.classfinder.extract.TextFileReaderTest;
import ru.devsand.classfinder.extract.TextSupplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class ExampleFileTest {

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

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"M", Arrays.asList("codeborne.MindReader", "codeborne.WishMaker")},
                {"WM", singletonList("codeborne.WishMaker")},
                {"M*R ", singletonList("codeborne.MindReader")},
                {"MaR ", emptyList()},
                {"Op ", Arrays.asList("ScubaArgentineOperator", "TelephoneOperator")},
                {"sao ", singletonList("ScubaArgentineOperator")},
                {"YA", singletonList("YourEyesAreSpinningInTheirSockets")},
                {"YA ", emptyList()},
                {"You", Arrays.asList("YourEyesAreSpinningInTheirSockets",
                        "YoureLeavingUsHere", "YouveComeToThisPoint")},
                {"nonsense", emptyList()}
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public String pattern;

    @Parameterized.Parameter(value = 1)
    public List<String> remainedClassNames;


    @Test
    public void checkSearch() {
        assertThat(classNameFinder.find(pattern), is(remainedClassNames));
    }

}
