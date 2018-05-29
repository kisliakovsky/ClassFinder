package ru.devsand.classfinder.stringwrap;

import ru.devsand.classfinder.util.StringUtil;

import java.util.List;

public class CamelCaseClassName implements ClassName<CamelCaseClassName> {

    private final String fullName;
    private final String packageName;
    private final String simpleClassName;

    public CamelCaseClassName(String className) {
        fullName = className;
        List<String> classNameParts = StringUtil.split(className, '.');
        packageName = separatePackageName(classNameParts);
        simpleClassName = separateSimpleClassName(classNameParts);
    }

    private static String separatePackageName(List<String> classNameParts) {
        return "";
    }

    private static String separateSimpleClassName(List<String> classNameParts) {
        return "";
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
    public int compareTo(CamelCaseClassName o) {
        return 0;
    }

}
