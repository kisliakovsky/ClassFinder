package ru.devsand.classfinder.search;

import ru.devsand.classfinder.pattern.CamelCasePattern;
import ru.devsand.classfinder.pattern.ClassNamePattern;
import ru.devsand.classfinder.stringwrap.CamelCaseName;

import java.util.Collection;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class SimpleClassNameFinder implements ClassNameFinder {

    private final Collection<String> classNames;

    public SimpleClassNameFinder(Supplier<Collection<String>> dataSupplier) {
        this.classNames = dataSupplier.get();
    }

    public Collection<String> find(String pattern) {
        ClassNamePattern classNamePattern = new CamelCasePattern(pattern);
        return classNames.stream()
                .map(CamelCaseName::new)
                .filter(className -> classNamePattern.match(className.getSimpleClassName()))
                .sorted()
                .map(CamelCaseName::getFullName)
                .collect(toList());
    }

}
