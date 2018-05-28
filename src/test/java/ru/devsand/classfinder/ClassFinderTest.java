package ru.devsand.classfinder;

import org.junit.Test;

import static ru.devsand.classfinder.ClassFinder.main;


public class ClassFinderTest {

    @Test
    public void checkMainWithOkArgs() {
        main(new String[]{"first", "second"});
    }

    @Test
    public void checkMainWithoutArgs() {
        main(new String[]{});
    }

    @Test
    public void checkMainWithHelpArg() {
        main(new String[]{"--help"});
    }

    @Test
    public void checkMainWithNotHelpArg() {
        main(new String[]{"abracadabra"});
    }

    @Test
    public void checkMainMoreThanTwoArgs() {
        main(new String[]{"first", "second", "third"});
    }

}