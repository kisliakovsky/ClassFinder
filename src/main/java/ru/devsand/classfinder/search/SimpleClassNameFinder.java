package ru.devsand.classfinder.search;

import ru.devsand.classfinder.pattern.SimpleClassNamePattern;
import ru.devsand.classfinder.pattern.ClassNamePattern;
import ru.devsand.classfinder.name.SimpleClassName;

import java.util.Collection;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class SimpleClassNameFinder implements ClassNameFinder {

    private final Collection<String> classNames;

    public SimpleClassNameFinder(Supplier<Collection<String>> dataSupplier) {
        this.classNames = dataSupplier.get();
    }

    public Collection<String> find(String pattern) {
        ClassNamePattern classNamePattern = new SimpleClassNamePattern(pattern);
        return classNames.stream()
                .map(SimpleClassName::new)
                .filter(className -> classNamePattern.match(className.getShortClassName()))
                .sorted()
                .map(SimpleClassName::getFullName)
                .collect(toList());
    }

}
