package ru.devsand.classfinder.name;

import ru.devsand.classfinder.util.StringSplitters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleClassName implements ClassName<SimpleClassName> {

    private static final char PACKAGE_SEPARATOR = '.';

    private final String fullName;
    private final String packageName;
    private final String shortClassName;

    public SimpleClassName(String className) {
        fullName = className;
        List<String> classNameParts = StringSplitters.split(className, PACKAGE_SEPARATOR);
        packageName = separatePackageName(classNameParts);
        shortClassName = separateSimpleClassName(classNameParts);
    }

    private static String separatePackageName(List<String> classNameParts) {
        int lastIndex = classNameParts.size() - 1;
        if (classNameParts.size() > 1) {
            List<String> packageNameParts = new ArrayList<>(classNameParts);
            packageNameParts.remove(lastIndex);
            return packageNameParts.stream()
                    .collect(Collectors.joining(String.valueOf(PACKAGE_SEPARATOR)));
        } else {
            return "";
        }
    }

    private static String separateSimpleClassName(List<String> classNameParts) {
        int lastIndex = classNameParts.size() - 1;
        return classNameParts.get(lastIndex);
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getShortClassName() {
        return shortClassName;
    }

    @Override
    public int compareTo(SimpleClassName another) {
        return this.shortClassName.compareTo(another.shortClassName);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
