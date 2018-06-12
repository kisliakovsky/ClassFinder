package ru.devsand.classfinder.name;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleClassNameTest {

    @Test
    public void checkClassNamesMustSortAlphabeticallyIgnoringPackageNames() {
        List<String> sortedClassNames = Stream.of("a.b.Wind", "n.x.Aqua", "z.mm.Pattern")
                .map(SimpleClassName::new)
                .sorted()
                .map(SimpleClassName::toString)
                .collect(toList());
        assertThat(sortedClassNames, equalTo(Arrays.asList("n.x.Aqua", "z.mm.Pattern", "a.b.Wind")));
    }

}
