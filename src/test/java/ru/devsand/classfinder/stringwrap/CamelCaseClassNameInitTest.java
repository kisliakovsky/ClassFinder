package ru.devsand.classfinder.stringwrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CamelCaseClassNameInitTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new CamelCaseClassName("FooBarBaz"), "FooBarBaz", "", "FooBarBaz"},
                {new CamelCaseClassName("a.b.FooBarBaz"), "a.b.FooBarBaz", "a.b", "FooBarBaz"},
                {new CamelCaseClassName("c.d.FooBar"), "c.d.FooBar", "c.d", "FooBar"},
        });
    }

    @SuppressWarnings("DefaultAnnotationParam")
    @Parameterized.Parameter(value = 0)
    public ClassName className;

    @Parameterized.Parameter(value = 1)
    public String fullName;

    @Parameterized.Parameter(value = 2)
    public String packageName;

    @Parameterized.Parameter(value = 3)
    public String simpleClassName;

    @Test
    public void checkNewInstances() {
        assertThat(className.getFullName(), is(fullName));
        assertThat(className.getPackageName(), is(packageName));
        assertThat(className.getSimpleClassName(), is(simpleClassName));
    }

}