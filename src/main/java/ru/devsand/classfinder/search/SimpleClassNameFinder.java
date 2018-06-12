package ru.devsand.classfinder.search;

import ru.devsand.classfinder.extract.TextSupplier;
import ru.devsand.classfinder.name.SimpleClassName;
import ru.devsand.classfinder.pattern.ClassNamePattern;
import ru.devsand.classfinder.pattern.SimpleClassNamePattern;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SimpleClassNameFinder implements ClassNameFinder {

    private final List<String> classNames;

    public SimpleClassNameFinder(TextSupplier dataSupplier) {
        this.classNames = dataSupplier.get();
    }

    public List<String> find(String pattern) {
        ClassNamePattern classNamePattern = new SimpleClassNamePattern(pattern);
        return classNames.stream()
                .map(SimpleClassName::new)
                .filter(className -> classNamePattern.match(className.getShortClassName()))
                .sorted()
                .map(SimpleClassName::getFullName)
                .collect(toList());
    }

}
