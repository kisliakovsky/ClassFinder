package ru.devsand.classfinder.search;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

public class SimpleClassFinder implements ClassFinder {

    private final Collection<String> classNames;

    public SimpleClassFinder(Supplier<Collection<String>> dataSupplier) {
        this.classNames = dataSupplier.get();
    }

    public Collection<String> find(String pattern) {
        return Collections.emptySet();
    }

}
