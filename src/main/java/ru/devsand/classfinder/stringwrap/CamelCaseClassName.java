package ru.devsand.classfinder.stringwrap;

import ru.devsand.classfinder.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CamelCaseClassName implements ClassName<CamelCaseClassName> {

    private static final char PACKAGE_SEPARATOR = '.';

    private final String fullName;
    private final String packageName;
    private final String simpleClassName;

    public CamelCaseClassName(String className) {
        fullName = className;
        List<String> classNameParts = StringUtil.split(className, PACKAGE_SEPARATOR);
        packageName = separatePackageName(classNameParts);
        simpleClassName = separateSimpleClassName(classNameParts);
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
    public String getSimpleClassName() {
        return simpleClassName;
    }

    @Override
    public int compareTo(CamelCaseClassName another) {
        return this.simpleClassName.compareTo(another.simpleClassName);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
