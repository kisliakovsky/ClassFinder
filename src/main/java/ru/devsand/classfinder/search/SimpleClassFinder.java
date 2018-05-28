package ru.devsand.classfinder.search;

import ru.devsand.classfinder.pattern.CamelCasePattern;
import ru.devsand.classfinder.pattern.ClassNamePattern;
import ru.devsand.classfinder.string.ClassName;

import java.util.Collection;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;

public class SimpleClassFinder implements ClassFinder {

    private final Collection<String> classNames;

    public SimpleClassFinder(Supplier<Collection<String>> dataSupplier) {
        this.classNames = dataSupplier.get();
    }

    public Collection<String> find(String pattern) {
        ClassNamePattern classNamePattern = new CamelCasePattern(pattern);
        return classNames.stream()
                .map(ClassName::new)
                .filter(className -> classNamePattern.match(className.getSimpleClassName()))
                .sorted()
                .map(ClassName::getFullName)
                .collect(toSet());
    }

}
